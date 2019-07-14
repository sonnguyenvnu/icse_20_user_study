private void handleNumericMetricAggregation(List<String> header,List<Object> line,Aggregation aggregation) throws ObjectResultsExtractException {
  String name=aggregation.getName();
  if (aggregation instanceof NumericMetricsAggregation.SingleValue) {
    if (!header.contains(name)) {
      header.add(name);
    }
    line.add(((NumericMetricsAggregation.SingleValue)aggregation).value());
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
      line.add(stats.getCount());
      line.add(stats.getSum());
      line.add(stats.getAvg());
      line.add(stats.getMin());
      line.add(stats.getMax());
      if (isExtendedStats) {
        ExtendedStats extendedStats=(ExtendedStats)aggregation;
        line.add(extendedStats.getSumOfSquares());
        line.add(extendedStats.getVariance());
        line.add(extendedStats.getStdDeviation());
      }
    }
 else     if (aggregation instanceof Percentiles) {
      String[] percentileHeaders=new String[]{"1.0","5.0","25.0","50.0","75.0","95.0","99.0"};
      mergeHeadersWithPrefix(header,name,percentileHeaders);
      Percentiles percentiles=(Percentiles)aggregation;
      line.add(percentiles.percentile(1.0));
      line.add(percentiles.percentile(5.0));
      line.add(percentiles.percentile(25.0));
      line.add(percentiles.percentile(50.0));
      line.add(percentiles.percentile(75));
      line.add(percentiles.percentile(95.0));
      line.add(percentiles.percentile(99.0));
    }
 else {
      throw new ObjectResultsExtractException("unknown NumericMetricsAggregation.MultiValue:" + aggregation.getClass());
    }
  }
 else {
    throw new ObjectResultsExtractException("unknown NumericMetricsAggregation" + aggregation.getClass());
  }
}
