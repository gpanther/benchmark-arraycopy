package net.greypanther;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkIntArrayCopy extends AbstractBenchmark {
	@Param({ "1", "10", "1000", "10000" })
	public int arraySizeInKb;

	public int[] intSource, intTarget;

	public BenchmarkIntArrayCopy() {
		super(int.class, Integer.BYTES);
	}

	@Override
	void allocate(int arrayLength) {
		intSource = new int[arrayLength];
		intTarget = new int[arrayLength];
	}

	@Override
	void setInput(int index, int value) {
		intSource[index] = (int) value;
	}

	@Override
	void copy(int index) {
		intTarget[index] = intSource[index];
	}

	@Override
	void selfCopy(int sourceIndex, int targetIndex) {
		intSource[targetIndex] = intSource[sourceIndex];
	}

	@Override
	Object getSource() {
		return intSource;
	}

	@Override
	Object getTarget() {
		return intTarget;
	}

	@Override
	int getArraySizeInKb() {
		return arraySizeInKb;
	}
}
