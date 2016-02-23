package net.greypanther;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkLongArrayCopy extends AbstractBenchmark {
	@Param({ "1", "10", "1000", "10000" })
	public int arraySizeInKb;

	public long[] longSource, longTarget;

	public BenchmarkLongArrayCopy() {
		super(long.class, Long.BYTES);
	}

	@Override
	void allocate(int arrayLength) {
		longSource = new long[arrayLength];
		longTarget = new long[arrayLength];
	}

	@Override
	void setInput(int index, int value) {
		longSource[index] = (long) value;
	}

	@Override
	void copy(int index) {
		longTarget[index] = longSource[index];
	}

	@Override
	void selfCopy(int sourceIndex, int targetIndex) {
		longSource[targetIndex] = longSource[sourceIndex];
	}

	@Override
	Object getSource() {
		return longSource;
	}

	@Override
	Object getTarget() {
		return longTarget;
	}

	@Override
	int getArraySizeInKb() {
		return arraySizeInKb;
	}
}
