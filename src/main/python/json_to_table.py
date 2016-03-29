import sys
import json

data = json.loads(open(sys.argv[1]).read())

print "\t".join(["name", "min", "lowBound", "highBound", "max", "sourceOffset", "destinationOffset"])

for benchmark in data:
	name = benchmark['benchmark']
	name = name.replace('net.greypanther.BenchmarkAlignment.', '')
	src_offset = int(benchmark['params']['sourceOffset'])
	dest_offset = int(benchmark['params']['destinationOffset'])
	name = '%s_%d_%d' % (name, src_offset, dest_offset)
	_min = benchmark['primaryMetric']['scorePercentiles']['0.0']
	_max = benchmark['primaryMetric']['scorePercentiles']['100.0']
	_avg = benchmark['primaryMetric']['score']
	_error = benchmark['primaryMetric']['scoreError']

	print "\t".join(str(v) for v in
		[name, _min, _avg - _error, _avg + _error, _max, src_offset, dest_offset])

