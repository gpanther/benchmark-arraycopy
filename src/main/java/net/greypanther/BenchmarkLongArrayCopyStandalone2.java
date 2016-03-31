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

@Warmup(iterations = 20, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 20, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(0)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@CompilerControl(CompilerControl.Mode.DONT_INLINE)
public class BenchmarkLongArrayCopyStandalone2 {
	private static final int MAXIMUM_SIZE = 10_000;
	private static final boolean ALIGN_FIRST_ELEMENT = System.getProperties().containsKey("alignFirstElement");

	@SuppressWarnings("unused")
	private static byte[] padding1, padding2;
	private static long[] source, destination;
	private static long sourceAddress, destinationAddress;

	static {
		Random randomRandom = new Random();

		while (true) {
			padding1 = new byte[randomRandom.nextInt(64)];
			source = new long[MAXIMUM_SIZE];

			for (int i = 0; i < 10; ++i) {
				System.gc();
				sleep(100, TimeUnit.MILLISECONDS);
			}

			long sourceAddressAlignment = alignment(source);
			if (sourceAddressAlignment == 0) {
				break;
			} else {
				System.out.print(sourceAddressAlignment + " ");
			}
		}
		System.out.println("Allocated source");

		while (true) {
			padding2 = new byte[randomRandom.nextInt(64)];
			destination = new long[MAXIMUM_SIZE];

			for (int i = 0; i < 10; ++i) {
				System.gc();
				sleep(100, TimeUnit.MILLISECONDS);
			}

			long destinationAddressAlignment = alignment(destination);
			if (destinationAddressAlignment == 0) {
				break;
			} else {
				System.out.print(destinationAddressAlignment + " ");
			}
		}
		System.out.println("Allocated destination");

		Random r = new Random(42);
		for (int i = 0; i < MAXIMUM_SIZE; ++i) {
			source[i] = r.nextInt();
		}

		System.out.println("Source alignment: " + alignment(source));
		System.out.println("Destination alignment: " + alignment(destination));

		sourceAddress = VMSupport.addressOf(source);
		destinationAddress = VMSupport.addressOf(destination);
	}

	@Param({ "1024" })
	int size;

	@Benchmark
	public final void arraycopy() {
		System.arraycopy(source, 0, destination, 0, size);
	}

	private static void sleep(long duration, TimeUnit timeUnit) {
		try {
			Thread.sleep(timeUnit.toMillis(duration));
		} catch (InterruptedException e) {
			throw new AssertionError();
		}
	}

	@Benchmark
	public final void manualCopy() {
		for (int i = 0; i < size; i++) {
			destination[i] = source[i];
		}
	}

	@Benchmark
	public final void manualCopy_Dec() {
		for (int i = size - 1; i >= 0; i--) {
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

	@SuppressWarnings("restriction")
	private static long alignment(long[] array) {
		long address = VMSupport.addressOf(array);
		if (ALIGN_FIRST_ELEMENT) {
			address += sun.misc.Unsafe.ARRAY_LONG_BASE_OFFSET;
		}
		return address % 64;
	}
}
