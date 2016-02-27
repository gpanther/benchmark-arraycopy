package net.greypanther;

import java.util.Random;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public final class CheckArrayAlignments_JustAllocate {
	private static final Unsafe UNSAFE = AbstractBenchmark.UNSAFE; 
			
	private static long getObjectAddress(Object o) {
		Object[] objectArray = { o };

		long baseOffset = Unsafe.ARRAY_OBJECT_BASE_OFFSET;
		int addressSize = UNSAFE.addressSize();
		long address;
		switch (addressSize) {
		case 4:
			address = UNSAFE.getInt(objectArray, baseOffset);
			break;
		case 8:
			address = UNSAFE.getLong(objectArray, baseOffset);
			break;
		default:
			throw new Error("unsupported address size: " + addressSize);
		}

		return address;
	}

	private static String getAlignment(long address) {
		StringBuilder result = new StringBuilder(4);
		result.append(address % 4 == 0 ? '4' : '-');
		result.append(address % 8 == 0 ? '8' : '-');
		result.append(address % 16 == 0 ? '6' : '-');
		result.append(address % 32 == 0 ? '2' : '-');
		return result.toString();
	}

	static void printObjectAlignment(Object o) {
		long objectAddress = getObjectAddress(o);
		long firstItemAddress = objectAddress + UNSAFE.arrayBaseOffset(o.getClass());
		System.out.println(getAlignment(objectAddress) + "\t" + getAlignment(firstItemAddress));
	}

	public static void main(String[] args) throws Exception {
		Random r = new Random();
		while (true) {
			printObjectAlignment(new long[r.nextInt(1024)]);
			Thread.sleep(100);
		}
	}
}
