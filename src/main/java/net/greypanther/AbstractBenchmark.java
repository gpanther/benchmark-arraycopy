package net.greypanther;

import java.lang.reflect.Field;
import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public abstract class AbstractBenchmark {
	private static final Unsafe UNSAFE = getUnsafe();

	private final long arrayBaseOffset, indexScale;
	private final int arrayItemSizeBytes;
	private int itemCount;


	AbstractBenchmark(Class<?> arrayItemClazz, int arrayItemSizeBytes) {
		if (arrayItemClazz == byte.class) {
			arrayBaseOffset = Unsafe.ARRAY_BYTE_BASE_OFFSET;
			indexScale = Unsafe.ARRAY_BYTE_INDEX_SCALE;
		} else if (arrayItemClazz == short.class) {
			arrayBaseOffset = Unsafe.ARRAY_SHORT_BASE_OFFSET;
			indexScale = Unsafe.ARRAY_SHORT_INDEX_SCALE;
		} else if (arrayItemClazz == int.class) {
			arrayBaseOffset = Unsafe.ARRAY_INT_BASE_OFFSET;
			indexScale = Unsafe.ARRAY_INT_INDEX_SCALE;
		} else if (arrayItemClazz == long.class) {
			arrayBaseOffset = Unsafe.ARRAY_LONG_BASE_OFFSET;
			indexScale = Unsafe.ARRAY_LONG_INDEX_SCALE;
		} else if (arrayItemClazz == Object.class) {
			arrayBaseOffset = Unsafe.ARRAY_OBJECT_BASE_OFFSET;
			indexScale = Unsafe.ARRAY_OBJECT_INDEX_SCALE;
			arrayItemSizeBytes = (int) indexScale;
		} else {
			throw new IllegalArgumentException(arrayItemClazz.getName());
		}
		this.arrayItemSizeBytes = arrayItemSizeBytes;
	}

	abstract void allocate(int arrayLength);

	abstract void setInput(int index, int value);

	abstract void copy(int index);

	abstract void selfCopy(int sourceIndex, int targetIndex);

	abstract Object getSource();

	abstract Object getTarget();

	abstract int getArraySizeInKb();

	@Benchmark
	public final void benchmarkArraySystemArraycopy() {
		System.arraycopy(getSource(), 0, getTarget(), 0, itemCount);
	}

	@Benchmark
	public final void benchmarkArrayUnsafeCopy() {
		UNSAFE.copyMemory(getSource(), arrayBaseOffset, getTarget(), arrayBaseOffset, itemCount * indexScale);
	}

	@Benchmark
	public final void benchmarkArrayManualCopy() {
		for (int i = 0; i < itemCount; ++i) {
			copy(i);
		}
	}

	@Benchmark
	public final void benchmarkArrayManualCopy_Dec() {
		for (int i = itemCount - 1; i >= 0; --i) {
			copy(i);
		}
	}

	@Benchmark
	public final void benchmarkArraySystemArraycopy_Overlap() {
		System.arraycopy(getSource(), 0, getTarget(), 1, itemCount - 1);
	}

	@Benchmark
	public void benchmarkArrayManualCopy_Overlap_Dec() {
		for (int i = itemCount - 1; i > 0; --i) {
			selfCopy(i - 1, i);
		}
	}

	@Setup
	public void setUp() {
		this.itemCount = 1024 / arrayItemSizeBytes * getArraySizeInKb();
		allocate(itemCount);
		Random r = new Random(42);
		for (int i = 0; i < itemCount; ++i) {
			setInput(i, r.nextInt());
		}
	}

	private static Unsafe getUnsafe() {
		try {
			Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe) field.get(null);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}
}
