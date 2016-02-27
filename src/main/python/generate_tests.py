offsets = [0, 1, 3, 5, 7, 13, 19, 23, 29, 31]

for i in offsets:
	for j in offsets:
		print """	@Benchmark
	public final void arraycopy_%d_%d() {
		System.arraycopy(source, %d, destination, %d, size);
	}""" % (i, j, i, j)

