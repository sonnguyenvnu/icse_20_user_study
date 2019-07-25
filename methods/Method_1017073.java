public static MultiSummary summarize(List<ResultGroup> resultGroups){
  final Histogram.Builder keySize=Histogram.builder();
  final SeriesSetsSummarizer seriesSummarizer=new SeriesSetsSummarizer();
  final Histogram.Builder dataSize=Histogram.builder();
  Optional<Long> cadence=Optional.empty();
  for (  final ResultGroup rg : resultGroups) {
    keySize.add(rg.getKey().size());
    seriesSummarizer.add(rg.getSeries());
    dataSize.add(rg.getGroup().size());
    cadence=Optional.of(rg.getCadence());
  }
  return new MultiSummary(keySize.build(),seriesSummarizer.end(),dataSize.build(),cadence);
}
