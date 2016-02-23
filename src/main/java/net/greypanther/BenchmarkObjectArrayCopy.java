package net.greypanther;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkObjectArrayCopy extends AbstractBenchmark {
	@Param({ "1", "10", "1000", "10000" })
	public int arraySizeInKb;

	public Object[] objectSource, objectTarget;

	public BenchmarkObjectArrayCopy() {
		super(Object.class, -1);
	}

	@Override
	void allocate(int arrayLength) {
		objectSource = new Object[arrayLength];
		objectTarget = new Object[arrayLength];
	}

	@Override
	void setInput(int index, int value) {
		// do nothing
	}

	@Override
	void copy(int index) {
		objectTarget[index] = objectSource[index];
	}

	@Override
	void selfCopy(int sourceIndex, int targetIndex) {
		objectSource[targetIndex] = objectSource[sourceIndex];
	}

	@Override
	Object getSource() {
		return objectSource;
	}

	@Override
	Object getTarget() {
		return objectTarget;
	}

	@Override
	int getArraySizeInKb() {
		return arraySizeInKb;
	}
}
