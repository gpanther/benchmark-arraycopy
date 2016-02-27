package net.greypanther;

import java.util.Random;

import org.openjdk.jol.util.VMSupport;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public final class CheckArrayAlignments_JustAllocate {
	private static final Unsafe UNSAFE = AbstractBenchmark.UNSAFE; 
			
	private static String getAlignment(long address) {
		StringBuilder result = new StringBuilder(4);
		result.append(address % 4 == 0 ? '4' : '-');
		result.append(address % 8 == 0 ? '8' : '-');
		result.append(address % 16 == 0 ? '6' : '-');
		result.append(address % 32 == 0 ? '2' : '-');
		return result.toString();
	}

	static void printObjectAlignment(Object o) {
		long objectAddress = VMSupport.addressOf(o);
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
