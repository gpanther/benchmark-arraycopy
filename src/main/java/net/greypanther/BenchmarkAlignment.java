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

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(0)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@CompilerControl(CompilerControl.Mode.DONT_INLINE)
public class BenchmarkAlignment {
	@Param({ "1024" })
	int size;

	byte[] source, destination;

	@Benchmark
	public final void arraycopy_0_0() {
		System.arraycopy(source, 0, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_0_1() {
		System.arraycopy(source, 0, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_0_3() {
		System.arraycopy(source, 0, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_0_5() {
		System.arraycopy(source, 0, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_0_7() {
		System.arraycopy(source, 0, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_0_13() {
		System.arraycopy(source, 0, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_0_19() {
		System.arraycopy(source, 0, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_0_23() {
		System.arraycopy(source, 0, destination, 23, size);
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
	public final void arraycopy_1_3() {
		System.arraycopy(source, 1, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_1_5() {
		System.arraycopy(source, 1, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_1_7() {
		System.arraycopy(source, 1, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_1_13() {
		System.arraycopy(source, 1, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_1_19() {
		System.arraycopy(source, 1, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_1_23() {
		System.arraycopy(source, 1, destination, 23, size);
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
	public final void arraycopy_3_0() {
		System.arraycopy(source, 3, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_3_1() {
		System.arraycopy(source, 3, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_3_3() {
		System.arraycopy(source, 3, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_3_5() {
		System.arraycopy(source, 3, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_3_7() {
		System.arraycopy(source, 3, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_3_13() {
		System.arraycopy(source, 3, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_3_19() {
		System.arraycopy(source, 3, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_3_23() {
		System.arraycopy(source, 3, destination, 23, size);
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
	public final void arraycopy_5_0() {
		System.arraycopy(source, 5, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_5_1() {
		System.arraycopy(source, 5, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_5_3() {
		System.arraycopy(source, 5, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_5_5() {
		System.arraycopy(source, 5, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_5_7() {
		System.arraycopy(source, 5, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_5_13() {
		System.arraycopy(source, 5, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_5_19() {
		System.arraycopy(source, 5, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_5_23() {
		System.arraycopy(source, 5, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_5_29() {
		System.arraycopy(source, 5, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_5_31() {
		System.arraycopy(source, 5, destination, 31, size);
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
	public final void arraycopy_7_3() {
		System.arraycopy(source, 7, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_7_5() {
		System.arraycopy(source, 7, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_7_7() {
		System.arraycopy(source, 7, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_7_13() {
		System.arraycopy(source, 7, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_7_19() {
		System.arraycopy(source, 7, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_7_23() {
		System.arraycopy(source, 7, destination, 23, size);
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
	public final void arraycopy_13_0() {
		System.arraycopy(source, 13, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_13_1() {
		System.arraycopy(source, 13, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_13_3() {
		System.arraycopy(source, 13, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_13_5() {
		System.arraycopy(source, 13, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_13_7() {
		System.arraycopy(source, 13, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_13_13() {
		System.arraycopy(source, 13, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_13_19() {
		System.arraycopy(source, 13, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_13_23() {
		System.arraycopy(source, 13, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_13_29() {
		System.arraycopy(source, 13, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_13_31() {
		System.arraycopy(source, 13, destination, 31, size);
	}

	@Benchmark
	public final void arraycopy_19_0() {
		System.arraycopy(source, 19, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_19_1() {
		System.arraycopy(source, 19, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_19_3() {
		System.arraycopy(source, 19, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_19_5() {
		System.arraycopy(source, 19, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_19_7() {
		System.arraycopy(source, 19, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_19_13() {
		System.arraycopy(source, 19, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_19_19() {
		System.arraycopy(source, 19, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_19_23() {
		System.arraycopy(source, 19, destination, 23, size);
	}

	@Benchmark
	public final void arraycopy_19_29() {
		System.arraycopy(source, 19, destination, 29, size);
	}

	@Benchmark
	public final void arraycopy_19_31() {
		System.arraycopy(source, 19, destination, 31, size);
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
	public final void arraycopy_23_3() {
		System.arraycopy(source, 23, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_23_5() {
		System.arraycopy(source, 23, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_23_7() {
		System.arraycopy(source, 23, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_23_13() {
		System.arraycopy(source, 23, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_23_19() {
		System.arraycopy(source, 23, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_23_23() {
		System.arraycopy(source, 23, destination, 23, size);
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
	public final void arraycopy_29_0() {
		System.arraycopy(source, 29, destination, 0, size);
	}

	@Benchmark
	public final void arraycopy_29_1() {
		System.arraycopy(source, 29, destination, 1, size);
	}

	@Benchmark
	public final void arraycopy_29_3() {
		System.arraycopy(source, 29, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_29_5() {
		System.arraycopy(source, 29, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_29_7() {
		System.arraycopy(source, 29, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_29_13() {
		System.arraycopy(source, 29, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_29_19() {
		System.arraycopy(source, 29, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_29_23() {
		System.arraycopy(source, 29, destination, 23, size);
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
	public final void arraycopy_31_3() {
		System.arraycopy(source, 31, destination, 3, size);
	}

	@Benchmark
	public final void arraycopy_31_5() {
		System.arraycopy(source, 31, destination, 5, size);
	}

	@Benchmark
	public final void arraycopy_31_7() {
		System.arraycopy(source, 31, destination, 7, size);
	}

	@Benchmark
	public final void arraycopy_31_13() {
		System.arraycopy(source, 31, destination, 13, size);
	}

	@Benchmark
	public final void arraycopy_31_19() {
		System.arraycopy(source, 31, destination, 19, size);
	}

	@Benchmark
	public final void arraycopy_31_23() {
		System.arraycopy(source, 31, destination, 23, size);
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
		Random r = new Random(42);

		source = new byte[size + 32];
		destination = new byte[size + 32];

		for (int i = 0; i < size; ++i) {
			source[i] = (byte) r.nextInt();
		}

		// Promote both arrays to old-gen
		System.gc();
	}
}
