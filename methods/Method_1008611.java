public static HistogramAggregationBuilder parse(String aggregationName,XContentParser parser) throws IOException {
  return PARSER.parse(parser,new HistogramAggregationBuilder(aggregationName),null);
}
