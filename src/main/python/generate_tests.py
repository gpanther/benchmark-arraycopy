offsets = [0, 1, 2, 3, 7, 8, 9, 15, 16, 17, 21, 23, 24, 27, 29, 31]

for i in offsets:
	for j in offsets:
		print """	@Benchmark
	public final void arraycopy_%d_%d() {
		System.arraycopy(source, %d, destination, %d, size);
	}""" % (i, j, i, j)

