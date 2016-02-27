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

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@CompilerControl(CompilerControl.Mode.DONT_INLINE)
public class BenchmarkLongArrayCopyStandalone {

	@Param({ "1024" })
	int size;

	byte[] pad;
	long[] source, destination;

	@Setup
	public void setUp() {
		Random r = new Random(42);

		pad = new byte[new Random().nextInt(1024)];
		source = new long[size];
		destination = new long[size];

		for (int i = 0; i < size; ++i) {
			source[i] = r.nextInt();
		}

		// Promote the shit out of both arrays
		System.gc();
	}

	@Benchmark
	public void arraycopy() {
		System.arraycopy(source, 0, destination, 0, size);
	}

	@Benchmark
	public void manualCopy() {
		for (int i = 0; i < size; i++) {
			destination[i] = source[i];
		}
	}
}