private List<Object> fillHeaderAndCreateLineForNumericAggregations(Aggregations aggregations,List<String> header) throws ObjectResultsExtractException {
  List<Object> line=new ArrayList<>();
  List<Aggregation> aggregationList=aggregations.asList();
  for (  Aggregation aggregation : aggregationList) {
    handleNumericMetricAggregation(header,line,aggregation);
  }
  return line;
}
