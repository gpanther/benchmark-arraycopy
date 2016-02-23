package net.greypanther;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkByteArrayCopy extends AbstractBenchmark {
	@Param({ "1", "10", "1000", "10000" })
	public int arraySizeInKb;

	public byte[] byteSource, byteTarget;

	public BenchmarkByteArrayCopy() {
		super(byte.class, Byte.BYTES);
	}

	@Override
	void allocate(int arrayLength) {
		byteSource = new byte[arrayLength];
		byteTarget = new byte[arrayLength];
	}

	@Override
	void setInput(int index, int value) {
		byteSource[index] = (byte) value;
	}

	@Override
	void copy(int index) {
		byteTarget[index] = byteSource[index];
	}

	@Override
	void selfCopy(int sourceIndex, int targetIndex) {
		byteSource[targetIndex] = byteSource[sourceIndex];
	}

	@Override
	Object getSource() {
		return byteSource;
	}

	@Override
	Object getTarget() {
		return byteTarget;
	}

	@Override
	int getArraySizeInKb() {
		return arraySizeInKb;
	}
}
