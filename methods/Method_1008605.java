static DateHistogramValuesSourceBuilder parse(String name,XContentParser parser) throws IOException {
  return PARSER.parse(parser,new DateHistogramValuesSourceBuilder(name),null);
}
