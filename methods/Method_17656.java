private Comparator<PolicyStats> makeComparator(){
switch (settings.report().sortBy().toLowerCase(US)) {
case "policy":
    return Comparator.comparing(PolicyStats::name);
case "hit rate":
  return Comparator.comparingDouble(PolicyStats::hitRate);
case "hits":
return Comparator.comparingLong(PolicyStats::hitCount);
case "misses":
return Comparator.comparingLong(PolicyStats::missCount);
case "evictions":
return Comparator.comparingLong(PolicyStats::evictionCount);
case "admit rate":
return Comparator.comparingLong(PolicyStats::admissionCount);
case "steps":
return Comparator.comparingLong(PolicyStats::operationCount);
case "time":
return Comparator.comparingLong(stats -> stats.stopwatch().elapsed(TimeUnit.NANOSECONDS));
default :
throw new IllegalArgumentException("Unknown sort order: " + settings.report().sortBy());
}
}
