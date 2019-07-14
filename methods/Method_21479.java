private List<String> fillHeaderAndCreateLineForNumericAggregations(Aggregations aggregations,List<String> header) throws CsvExtractorException {
  List<String> line=new ArrayList<>();
  List<Aggregation> aggregationList=aggregations.asList();
  for (  Aggregation aggregation : aggregationList) {
    handleNumericMetricAggregation(header,line,aggregation);
  }
  return line;
}
