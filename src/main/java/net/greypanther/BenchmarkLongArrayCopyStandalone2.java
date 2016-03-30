package net.greypanther;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Mode;
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
public class BenchmarkLongArrayCopyStandalone2 {
	private static final int MAXIMUM_SIZE = 10_000;
	private static final int ALIGNMENT = 64;

	private static long sourceAddress, destinationAddress;
	private static long[] source, destination;

	@Param({ "1024" })
	int size;

	@Benchmark
	public final void arraycopy() {
		System.arraycopy(source, 6, destination, 6, size);
	}

	@Benchmark
	public final void manualCopy() {
		for (int i = 6; i < size; i++) {
			destination[i] = source[i];
		}
	}

	@Benchmark
	public final void manualCopy_Dec() {
		for (int i = size - 1; i >= 6; i--) {
			destination[i] = source[i];
		}
	}

	@Setup
	public void setUp() {
		if (VMSupport.addressOf(source) != sourceAddress) {
			throw new IllegalArgumentException("source");
		}

		if (VMSupport.addressOf(destination) != destinationAddress) {
			throw new IllegalArgumentException("destination");
		}
	}

	static {
		source = new long[MAXIMUM_SIZE];
		sourceAddress = VMSupport.addressOf(source);
		destination = new long[MAXIMUM_SIZE];
		destinationAddress = VMSupport.addressOf(destination);

		if (alignment(source) != 16 && alignment(destination) != 16) {
			throw new IllegalArgumentException(alignment(source) + " " + alignment(destination));
		}

		Random r = new Random(42);
		for (int i = 0; i < MAXIMUM_SIZE; ++i) {
			source[i] = r.nextInt();
		}
	}

	private static long alignment(long[] array) {
		long address = VMSupport.addressOf(array);
		@SuppressWarnings("restriction")
		long resultAlignment = (address + sun.misc.Unsafe.ARRAY_LONG_BASE_OFFSET) % ALIGNMENT;
		return resultAlignment;
	}
}
