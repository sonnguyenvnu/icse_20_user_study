private boolean allNumericAggregations(Aggregations aggregations){
  List<Aggregation> aggregationList=aggregations.asList();
  for (  Aggregation aggregation : aggregationList) {
    if (!(aggregation instanceof NumericMetricsAggregation)) {
      return false;
    }
  }
  return true;
}
