public static MultiSummary summarize(List<ShardedResultGroup> resultGroups){
  final ImmutableSet.Builder<Map<String,String>> shardSummary=ImmutableSet.builder();
  final Histogram.Builder keySize=Histogram.builder();
  final SeriesSetsSummarizer seriesSummarizer=new SeriesSetsSummarizer();
  final Histogram.Builder dataSize=Histogram.builder();
  Optional<Long> cadence=Optional.empty();
  for (  ShardedResultGroup rg : resultGroups) {
    shardSummary.add(rg.getShard());
    keySize.add(rg.getKey().size());
    seriesSummarizer.add(rg.getSeries());
    dataSize.add(rg.getMetrics().size());
    cadence=Optional.of(rg.getCadence());
  }
  return new MultiSummary(shardSummary.build(),keySize.build(),seriesSummarizer.end(),dataSize.build(),cadence);
}
