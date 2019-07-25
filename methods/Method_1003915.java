@Override public Map<String,Number> sample(){
  ImmutableMap.Builder<String,Number> samples=ImmutableMap.builder();
  samples.putAll(sampleGauges());
  samples.putAll(sampleCounters());
  for (  Map.Entry<String,Snapshot> entry : sampleHistograms().entrySet()) {
    String name=entry.getKey();
    Snapshot snapshot=entry.getValue();
    samples.put(named(name,"count"),snapshot.count());
    samples.put(named(name,"sum"),snapshot.sum());
    samples.put(named(name,"avg"),snapshot.avg());
    samples.put(named(name,"min"),snapshot.min());
    samples.put(named(name,"max"),snapshot.max());
    samples.put(named(name,"stddev"),snapshot.stddev());
    for (    Percentile p : snapshot.percentiles()) {
      String percentileName=named(name,gaugeName(p.getQuantile()));
      samples.put(percentileName,p.getValue());
    }
  }
  return samples.build();
}
