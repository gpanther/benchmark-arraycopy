package net.greypanther;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkShortArrayCopy extends AbstractBenchmark {
	@Param({ "1", "10", "1000", "10000" })
	public int arraySizeInKb;

	public short[] shortSource, shortTarget;

	public BenchmarkShortArrayCopy() {
		super(short.class, Short.BYTES);
	}

	@Override
	void allocate(int arrayLength) {
		shortSource = new short[arrayLength];
		shortTarget = new short[arrayLength];
	}

	@Override
	void setInput(int index, int value) {
		shortSource[index] = (short) value;
	}

	@Override
	void copy(int index) {
		shortTarget[index] = shortSource[index];
	}

	@Override
	void selfCopy(int sourceIndex, int targetIndex) {
		shortSource[targetIndex] = shortSource[sourceIndex];
	}

	@Override
	Object getSource() {
		return shortSource;
	}

	@Override
	Object getTarget() {
		return shortTarget;
	}

	@Override
	int getArraySizeInKb() {
		return arraySizeInKb;
	}
}
