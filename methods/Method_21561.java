private void handleAggregations(Aggregations aggregations,List<String> headers,List<List<Object>> lines) throws ObjectResultsExtractException {
  if (allNumericAggregations(aggregations)) {
    lines.get(this.currentLineIndex).addAll(fillHeaderAndCreateLineForNumericAggregations(aggregations,headers));
    return;
  }
  List<Aggregation> aggregationList=aggregations.asList();
  if (aggregationList.size() > 1) {
    throw new ObjectResultsExtractException("currently support only one aggregation at same level (Except for numeric metrics)");
  }
  Aggregation aggregation=aggregationList.get(0);
  if (aggregation instanceof SingleBucketAggregation) {
    Aggregations singleBucketAggs=((SingleBucketAggregation)aggregation).getAggregations();
    handleAggregations(singleBucketAggs,headers,lines);
    return;
  }
  if (aggregation instanceof NumericMetricsAggregation) {
    handleNumericMetricAggregation(headers,lines.get(currentLineIndex),aggregation);
    return;
  }
  if (aggregation instanceof GeoBounds) {
    handleGeoBoundsAggregation(headers,lines,(GeoBounds)aggregation);
    return;
  }
  if (aggregation instanceof TopHits) {
  }
  if (aggregation instanceof MultiBucketsAggregation) {
    MultiBucketsAggregation bucketsAggregation=(MultiBucketsAggregation)aggregation;
    String name=bucketsAggregation.getName();
    if (!headers.contains(name)) {
      headers.add(name);
    }
    Collection<? extends MultiBucketsAggregation.Bucket> buckets=bucketsAggregation.getBuckets();
    List<Object> currentLine=lines.get(this.currentLineIndex);
    List<Object> clonedLine=new ArrayList<>(currentLine);
    boolean firstLine=true;
    for (    MultiBucketsAggregation.Bucket bucket : buckets) {
      String key=bucket.getKeyAsString();
      if (firstLine) {
        firstLine=false;
      }
 else {
        currentLineIndex++;
        currentLine=new ArrayList<Object>(clonedLine);
        lines.add(currentLine);
      }
      currentLine.add(key);
      handleAggregations(bucket.getAggregations(),headers,lines);
    }
  }
}
