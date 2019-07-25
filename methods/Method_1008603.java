public static CompositeAggregationBuilder parse(String aggregationName,XContentParser parser) throws IOException {
  return PARSER.parse(parser,new CompositeAggregationBuilder(aggregationName),null);
}
