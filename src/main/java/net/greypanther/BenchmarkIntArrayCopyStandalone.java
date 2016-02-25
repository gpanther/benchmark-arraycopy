package net.greypanther;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class BenchmarkIntArrayCopyStandalone {
	private static final int[] SOURCE = createSourceOfSizeKb(10_000);
	private static final int[] DESTINATION = createDestionationOfSizeKb(10_000);

	@Param({ "1", "10" })
	public int sizeInKb;

	public int sizeInItems;

	@Benchmark
	public final void benchmarkArraySystemArraycopy() {
		System.arraycopy(SOURCE, 0, DESTINATION, 0, sizeInItems);
	}

	@Benchmark
	public final void benchmarkArrayManualCopy_Dec() {
		for (int i = sizeInItems - 1; i >= 0; --i) {
			DESTINATION[i] = SOURCE[i];
		}
	}

	@Setup
	public void setUp() {
		sizeInItems = sizeInKb * 1024 / Long.BYTES;
	}

	private static int[] createDestionationOfSizeKb(int sizeInKb) {
		int sizeInItems = sizeInKb * 1024 / Long.BYTES;
		int[] result = new int[sizeInItems];
		Random r = new Random(42);
		for (int i = 0; i < result.length; ++i) {
			result[i] = r.nextInt();
		}
		return result;
	}

	private static int[] createSourceOfSizeKb(int sizeInKb) {
		int sizeInItems = sizeInKb * 1024 / Long.BYTES;
		return new int[sizeInItems];
	}
}
