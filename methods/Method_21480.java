private void handleNumericMetricAggregation(List<String> header,List<String> line,Aggregation aggregation) throws CsvExtractorException {
  String name=aggregation.getName();
  if (aggregation instanceof NumericMetricsAggregation.SingleValue) {
    if (!header.contains(name)) {
      header.add(name);
    }
    NumericMetricsAggregation.SingleValue agg=(NumericMetricsAggregation.SingleValue)aggregation;
    line.add(!Double.isInfinite(agg.value()) ? agg.getValueAsString() : "null");
  }
 else   if (aggregation instanceof NumericMetricsAggregation.MultiValue) {
    if (aggregation instanceof Stats) {
      String[] statsHeaders=new String[]{"count","sum","avg","min","max"};
      boolean isExtendedStats=aggregation instanceof ExtendedStats;
      if (isExtendedStats) {
        String[] extendedHeaders=new String[]{"sumOfSquares","variance","stdDeviation"};
        statsHeaders=Util.concatStringsArrays(statsHeaders,extendedHeaders);
      }
      mergeHeadersWithPrefix(header,name,statsHeaders);
      Stats stats=(Stats)aggregation;
      line.add(String.valueOf(stats.getCount()));
      line.add(stats.getSumAsString());
      line.add(stats.getAvgAsString());
      line.add(stats.getMinAsString());
      line.add(stats.getMaxAsString());
      if (isExtendedStats) {
        ExtendedStats extendedStats=(ExtendedStats)aggregation;
        line.add(extendedStats.getSumOfSquaresAsString());
        line.add(extendedStats.getVarianceAsString());
        line.add(extendedStats.getStdDeviationAsString());
      }
    }
 else     if (aggregation instanceof Percentiles) {
      List<String> percentileHeaders=new ArrayList<>(7);
      Percentiles percentiles=(Percentiles)aggregation;
      for (      Percentile p : percentiles) {
        percentileHeaders.add(String.valueOf(p.getPercent()));
        line.add(percentiles.percentileAsString(p.getPercent()));
      }
      mergeHeadersWithPrefix(header,name,percentileHeaders.toArray(new String[0]));
    }
 else {
      throw new CsvExtractorException("unknown NumericMetricsAggregation.MultiValue:" + aggregation.getClass());
    }
  }
 else {
    throw new CsvExtractorException("unknown NumericMetricsAggregation" + aggregation.getClass());
  }
}
