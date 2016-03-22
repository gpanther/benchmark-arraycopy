package net.greypanther;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jol.util.VMSupport;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(0)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@CompilerControl(CompilerControl.Mode.DONT_INLINE)
public class BenchmarkAlignment {
	private static final int MAXIMUM_SIZE = 10_000;

	private static final byte[] source = fillRandomly(new byte[MAXIMUM_SIZE]);
	private static final byte[] destination = new byte[MAXIMUM_SIZE];
	private static final int sourceStartOffset = getStartOffsetDivisibleBy64(source);
	private static final int destinationStartOffset = getStartOffsetDivisibleBy64(destination);

	private static int getStartOffsetDivisibleBy64(byte[] array) {
		// GC to try to make sure that the array is promoted to old gen and won't move
		for (int i = 0; i < 10; ++i) {
			System.gc();
			sleep(100, TimeUnit.MILLISECONDS);
		}

		int i = 0;
		while (addressOfElement(array, i) % 64 != 0) {
			i++;
		}
		return i;
	}

	@SuppressWarnings("restriction")
	private static long addressOfElement(byte[] array, int index) {
		long address = VMSupport.addressOf(array);
		address += sun.misc.Unsafe.ARRAY_BYTE_BASE_OFFSET;
		address += index;
		return address;
	}

	private static byte[] fillRandomly(byte[] array) {
		Random r = new Random(42);
		for (int i = 0; i < array.length; ++i) {
			array[i] = (byte) r.nextInt();
		}
		return array;
	}

	private static void sleep(long duration, TimeUnit timeUnit) {
		try {
			Thread.sleep(timeUnit.toMillis(duration));
		} catch (InterruptedException e) {
			throw new AssertionError();
		}
	}

	@Param({ "1024" })
	int size;
	@Param({ "0", "1", "2", "3", "4", "5", "8", "13", "16", "21", "32", "34", "55", "64" })
	int sourceOffset;
	@Param({ "0", "1", "2", "3", "4", "5", "8", "13", "16", "21", "32", "34", "55", "64" })
	int destinationOffset;

	@Benchmark
	public final void arraycopy() {
		System.arraycopy(source, sourceStartOffset + sourceOffset, destination, destinationStartOffset + destinationOffset, size);
	}

	@Setup
	public void setUp() {
		if (addressOfElement(source, sourceStartOffset) % 64 != 0) {
			throw new IllegalArgumentException("source");
		}

		if (addressOfElement(destination, destinationStartOffset) % 64 != 0) {
			throw new IllegalArgumentException("destination");
		}
	}
}
