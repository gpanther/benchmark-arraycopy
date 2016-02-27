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

	@SuppressWarnings("unused")
	private static byte[] padding1, padding2;
	private static byte[] source, destination;

	static {
		Random randomRandom = new Random();

		while (true) {
			padding1 = new byte[randomRandom.nextInt(64)];
			source = new byte[MAXIMUM_SIZE];

			for (int i = 0; i < 10; ++i) {
				System.gc();
				sleep(100, TimeUnit.MILLISECONDS);
			}

			long sourceAddressAlignment = VMSupport.addressOf(source) % 32;
			if (sourceAddressAlignment == 0) {
				break;
			} else {
				System.out.print(sourceAddressAlignment + " ");
			}
		}
		System.out.println("Allocated source");

		while (true) {
			padding2 = new byte[randomRandom.nextInt(64)];
			destination = new byte[MAXIMUM_SIZE];

			for (int i = 0; i < 10; ++i) {
				System.gc();
				sleep(100, TimeUnit.MILLISECONDS);
			}

			long destinationAddressAlignment = VMSupport.addressOf(destination) % 32;
			if (destinationAddressAlignment == 0) {
				break;
			} else {
				System.out.print(destinationAddressAlignment + " ");
			}
		}
		System.out.println("Allocated destination");

		Random r = new Random(42);
		for (int i = 0; i < MAXIMUM_SIZE; ++i) {
			source[i] = (byte) r.nextInt();
		}
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

	@Benchmark
	public final void arraycopy_0_0() {
		System.arraycopy(source, 0, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_0_1() {
		System.arraycopy(source, 0, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_0_2() {
		System.arraycopy(source, 0, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_0_3() {
		System.arraycopy(source, 0, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_0_7() {
		System.arraycopy(source, 0, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_0_8() {
		System.arraycopy(source, 0, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_0_9() {
		System.arraycopy(source, 0, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_0_15() {
		System.arraycopy(source, 0, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_0_16() {
		System.arraycopy(source, 0, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_0_17() {
		System.arraycopy(source, 0, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_0_21() {
		System.arraycopy(source, 0, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_0_23() {
		System.arraycopy(source, 0, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_0_24() {
		System.arraycopy(source, 0, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_0_27() {
		System.arraycopy(source, 0, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_0_29() {
		System.arraycopy(source, 0, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_0_31() {
		System.arraycopy(source, 0, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_1_0() {
		System.arraycopy(source, 1, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_1_1() {
		System.arraycopy(source, 1, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_1_2() {
		System.arraycopy(source, 1, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_1_3() {
		System.arraycopy(source, 1, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_1_7() {
		System.arraycopy(source, 1, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_1_8() {
		System.arraycopy(source, 1, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_1_9() {
		System.arraycopy(source, 1, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_1_15() {
		System.arraycopy(source, 1, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_1_16() {
		System.arraycopy(source, 1, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_1_17() {
		System.arraycopy(source, 1, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_1_21() {
		System.arraycopy(source, 1, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_1_23() {
		System.arraycopy(source, 1, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_1_24() {
		System.arraycopy(source, 1, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_1_27() {
		System.arraycopy(source, 1, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_1_29() {
		System.arraycopy(source, 1, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_1_31() {
		System.arraycopy(source, 1, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_2_0() {
		System.arraycopy(source, 2, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_2_1() {
		System.arraycopy(source, 2, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_2_2() {
		System.arraycopy(source, 2, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_2_3() {
		System.arraycopy(source, 2, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_2_7() {
		System.arraycopy(source, 2, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_2_8() {
		System.arraycopy(source, 2, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_2_9() {
		System.arraycopy(source, 2, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_2_15() {
		System.arraycopy(source, 2, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_2_16() {
		System.arraycopy(source, 2, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_2_17() {
		System.arraycopy(source, 2, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_2_21() {
		System.arraycopy(source, 2, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_2_23() {
		System.arraycopy(source, 2, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_2_24() {
		System.arraycopy(source, 2, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_2_27() {
		System.arraycopy(source, 2, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_2_29() {
		System.arraycopy(source, 2, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_2_31() {
		System.arraycopy(source, 2, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_3_0() {
		System.arraycopy(source, 3, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_3_1() {
		System.arraycopy(source, 3, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_3_2() {
		System.arraycopy(source, 3, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_3_3() {
		System.arraycopy(source, 3, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_3_7() {
		System.arraycopy(source, 3, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_3_8() {
		System.arraycopy(source, 3, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_3_9() {
		System.arraycopy(source, 3, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_3_15() {
		System.arraycopy(source, 3, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_3_16() {
		System.arraycopy(source, 3, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_3_17() {
		System.arraycopy(source, 3, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_3_21() {
		System.arraycopy(source, 3, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_3_23() {
		System.arraycopy(source, 3, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_3_24() {
		System.arraycopy(source, 3, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_3_27() {
		System.arraycopy(source, 3, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_3_29() {
		System.arraycopy(source, 3, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_3_31() {
		System.arraycopy(source, 3, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_7_0() {
		System.arraycopy(source, 7, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_7_1() {
		System.arraycopy(source, 7, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_7_2() {
		System.arraycopy(source, 7, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_7_3() {
		System.arraycopy(source, 7, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_7_7() {
		System.arraycopy(source, 7, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_7_8() {
		System.arraycopy(source, 7, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_7_9() {
		System.arraycopy(source, 7, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_7_15() {
		System.arraycopy(source, 7, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_7_16() {
		System.arraycopy(source, 7, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_7_17() {
		System.arraycopy(source, 7, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_7_21() {
		System.arraycopy(source, 7, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_7_23() {
		System.arraycopy(source, 7, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_7_24() {
		System.arraycopy(source, 7, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_7_27() {
		System.arraycopy(source, 7, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_7_29() {
		System.arraycopy(source, 7, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_7_31() {
		System.arraycopy(source, 7, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_8_0() {
		System.arraycopy(source, 8, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_8_1() {
		System.arraycopy(source, 8, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_8_2() {
		System.arraycopy(source, 8, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_8_3() {
		System.arraycopy(source, 8, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_8_7() {
		System.arraycopy(source, 8, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_8_8() {
		System.arraycopy(source, 8, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_8_9() {
		System.arraycopy(source, 8, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_8_15() {
		System.arraycopy(source, 8, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_8_16() {
		System.arraycopy(source, 8, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_8_17() {
		System.arraycopy(source, 8, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_8_21() {
		System.arraycopy(source, 8, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_8_23() {
		System.arraycopy(source, 8, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_8_24() {
		System.arraycopy(source, 8, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_8_27() {
		System.arraycopy(source, 8, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_8_29() {
		System.arraycopy(source, 8, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_8_31() {
		System.arraycopy(source, 8, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_9_0() {
		System.arraycopy(source, 9, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_9_1() {
		System.arraycopy(source, 9, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_9_2() {
		System.arraycopy(source, 9, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_9_3() {
		System.arraycopy(source, 9, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_9_7() {
		System.arraycopy(source, 9, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_9_8() {
		System.arraycopy(source, 9, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_9_9() {
		System.arraycopy(source, 9, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_9_15() {
		System.arraycopy(source, 9, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_9_16() {
		System.arraycopy(source, 9, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_9_17() {
		System.arraycopy(source, 9, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_9_21() {
		System.arraycopy(source, 9, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_9_23() {
		System.arraycopy(source, 9, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_9_24() {
		System.arraycopy(source, 9, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_9_27() {
		System.arraycopy(source, 9, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_9_29() {
		System.arraycopy(source, 9, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_9_31() {
		System.arraycopy(source, 9, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_15_0() {
		System.arraycopy(source, 15, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_15_1() {
		System.arraycopy(source, 15, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_15_2() {
		System.arraycopy(source, 15, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_15_3() {
		System.arraycopy(source, 15, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_15_7() {
		System.arraycopy(source, 15, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_15_8() {
		System.arraycopy(source, 15, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_15_9() {
		System.arraycopy(source, 15, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_15_15() {
		System.arraycopy(source, 15, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_15_16() {
		System.arraycopy(source, 15, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_15_17() {
		System.arraycopy(source, 15, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_15_21() {
		System.arraycopy(source, 15, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_15_23() {
		System.arraycopy(source, 15, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_15_24() {
		System.arraycopy(source, 15, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_15_27() {
		System.arraycopy(source, 15, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_15_29() {
		System.arraycopy(source, 15, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_15_31() {
		System.arraycopy(source, 15, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_16_0() {
		System.arraycopy(source, 16, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_16_1() {
		System.arraycopy(source, 16, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_16_2() {
		System.arraycopy(source, 16, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_16_3() {
		System.arraycopy(source, 16, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_16_7() {
		System.arraycopy(source, 16, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_16_8() {
		System.arraycopy(source, 16, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_16_9() {
		System.arraycopy(source, 16, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_16_15() {
		System.arraycopy(source, 16, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_16_16() {
		System.arraycopy(source, 16, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_16_17() {
		System.arraycopy(source, 16, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_16_21() {
		System.arraycopy(source, 16, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_16_23() {
		System.arraycopy(source, 16, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_16_24() {
		System.arraycopy(source, 16, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_16_27() {
		System.arraycopy(source, 16, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_16_29() {
		System.arraycopy(source, 16, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_16_31() {
		System.arraycopy(source, 16, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_17_0() {
		System.arraycopy(source, 17, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_17_1() {
		System.arraycopy(source, 17, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_17_2() {
		System.arraycopy(source, 17, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_17_3() {
		System.arraycopy(source, 17, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_17_7() {
		System.arraycopy(source, 17, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_17_8() {
		System.arraycopy(source, 17, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_17_9() {
		System.arraycopy(source, 17, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_17_15() {
		System.arraycopy(source, 17, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_17_16() {
		System.arraycopy(source, 17, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_17_17() {
		System.arraycopy(source, 17, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_17_21() {
		System.arraycopy(source, 17, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_17_23() {
		System.arraycopy(source, 17, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_17_24() {
		System.arraycopy(source, 17, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_17_27() {
		System.arraycopy(source, 17, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_17_29() {
		System.arraycopy(source, 17, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_17_31() {
		System.arraycopy(source, 17, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_21_0() {
		System.arraycopy(source, 21, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_21_1() {
		System.arraycopy(source, 21, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_21_2() {
		System.arraycopy(source, 21, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_21_3() {
		System.arraycopy(source, 21, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_21_7() {
		System.arraycopy(source, 21, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_21_8() {
		System.arraycopy(source, 21, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_21_9() {
		System.arraycopy(source, 21, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_21_15() {
		System.arraycopy(source, 21, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_21_16() {
		System.arraycopy(source, 21, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_21_17() {
		System.arraycopy(source, 21, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_21_21() {
		System.arraycopy(source, 21, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_21_23() {
		System.arraycopy(source, 21, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_21_24() {
		System.arraycopy(source, 21, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_21_27() {
		System.arraycopy(source, 21, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_21_29() {
		System.arraycopy(source, 21, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_21_31() {
		System.arraycopy(source, 21, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_23_0() {
		System.arraycopy(source, 23, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_23_1() {
		System.arraycopy(source, 23, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_23_2() {
		System.arraycopy(source, 23, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_23_3() {
		System.arraycopy(source, 23, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_23_7() {
		System.arraycopy(source, 23, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_23_8() {
		System.arraycopy(source, 23, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_23_9() {
		System.arraycopy(source, 23, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_23_15() {
		System.arraycopy(source, 23, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_23_16() {
		System.arraycopy(source, 23, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_23_17() {
		System.arraycopy(source, 23, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_23_21() {
		System.arraycopy(source, 23, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_23_23() {
		System.arraycopy(source, 23, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_23_24() {
		System.arraycopy(source, 23, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_23_27() {
		System.arraycopy(source, 23, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_23_29() {
		System.arraycopy(source, 23, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_23_31() {
		System.arraycopy(source, 23, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_24_0() {
		System.arraycopy(source, 24, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_24_1() {
		System.arraycopy(source, 24, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_24_2() {
		System.arraycopy(source, 24, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_24_3() {
		System.arraycopy(source, 24, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_24_7() {
		System.arraycopy(source, 24, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_24_8() {
		System.arraycopy(source, 24, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_24_9() {
		System.arraycopy(source, 24, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_24_15() {
		System.arraycopy(source, 24, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_24_16() {
		System.arraycopy(source, 24, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_24_17() {
		System.arraycopy(source, 24, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_24_21() {
		System.arraycopy(source, 24, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_24_23() {
		System.arraycopy(source, 24, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_24_24() {
		System.arraycopy(source, 24, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_24_27() {
		System.arraycopy(source, 24, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_24_29() {
		System.arraycopy(source, 24, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_24_31() {
		System.arraycopy(source, 24, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_27_0() {
		System.arraycopy(source, 27, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_27_1() {
		System.arraycopy(source, 27, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_27_2() {
		System.arraycopy(source, 27, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_27_3() {
		System.arraycopy(source, 27, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_27_7() {
		System.arraycopy(source, 27, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_27_8() {
		System.arraycopy(source, 27, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_27_9() {
		System.arraycopy(source, 27, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_27_15() {
		System.arraycopy(source, 27, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_27_16() {
		System.arraycopy(source, 27, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_27_17() {
		System.arraycopy(source, 27, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_27_21() {
		System.arraycopy(source, 27, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_27_23() {
		System.arraycopy(source, 27, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_27_24() {
		System.arraycopy(source, 27, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_27_27() {
		System.arraycopy(source, 27, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_27_29() {
		System.arraycopy(source, 27, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_27_31() {
		System.arraycopy(source, 27, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_29_0() {
		System.arraycopy(source, 29, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_29_1() {
		System.arraycopy(source, 29, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_29_2() {
		System.arraycopy(source, 29, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_29_3() {
		System.arraycopy(source, 29, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_29_7() {
		System.arraycopy(source, 29, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_29_8() {
		System.arraycopy(source, 29, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_29_9() {
		System.arraycopy(source, 29, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_29_15() {
		System.arraycopy(source, 29, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_29_16() {
		System.arraycopy(source, 29, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_29_17() {
		System.arraycopy(source, 29, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_29_21() {
		System.arraycopy(source, 29, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_29_23() {
		System.arraycopy(source, 29, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_29_24() {
		System.arraycopy(source, 29, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_29_27() {
		System.arraycopy(source, 29, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_29_29() {
		System.arraycopy(source, 29, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_29_31() {
		System.arraycopy(source, 29, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_31_0() {
		System.arraycopy(source, 31, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_31_1() {
		System.arraycopy(source, 31, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_31_2() {
		System.arraycopy(source, 31, destination, 2, size);
	}

	@Benchmark
	public final void arraycopy_31_3() {
		System.arraycopy(source, 31, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_31_7() {
		System.arraycopy(source, 31, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_31_8() {
		System.arraycopy(source, 31, destination, 8, size);
	}

	@Benchmark
	public final void arraycopy_31_9() {
		System.arraycopy(source, 31, destination, 9, size);
	}

	@Benchmark
	public final void arraycopy_31_15() {
		System.arraycopy(source, 31, destination, 15, size);
	}

	@Benchmark
	public final void arraycopy_31_16() {
		System.arraycopy(source, 31, destination, 16, size);
	}

	@Benchmark
	public final void arraycopy_31_17() {
		System.arraycopy(source, 31, destination, 17, size);
	}

	@Benchmark
	public final void arraycopy_31_21() {
		System.arraycopy(source, 31, destination, 21, size);
	}

	@Benchmark
	public final void arraycopy_31_23() {
		System.arraycopy(source, 31, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_31_24() {
		System.arraycopy(source, 31, destination, 24, size);
	}

	@Benchmark
	public final void arraycopy_31_27() {
		System.arraycopy(source, 31, destination, 27, size);
	}

	@Benchmark
	public final void arraycopy_31_29() {
		System.arraycopy(source, 31, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_31_31() {
		System.arraycopy(source, 31, destination, 31, size);
	}

	@Setup
	public void setUp() {
		if (VMSupport.addressOf(source) % 32 != 0) {
			throw new IllegalArgumentException("source");
		}

		if (VMSupport.addressOf(destination) % 32 != 0) {
			throw new IllegalArgumentException("destination");
		}
	}
}
