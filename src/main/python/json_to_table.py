import sys
import json

data = json.loads(open(sys.argv[1]).read())

for benchmark in data:
	name = benchmark['benchmark']
	_min = benchmark['primaryMetric']['scorePercentiles']['0.0']
	_max = benchmark['primaryMetric']['scorePercentiles']['100.0']
	_avg = benchmark['primaryMetric']['score']
	_error = benchmark['primaryMetric']['scoreError']

	print "\t".join(str(v) for v in [name, _min, _avg - _error, _avg + _error, _max])

