public static SignificanceHeuristic parse(XContentParser parser) throws IOException, QueryShardException {
  if (!parser.nextToken().equals(XContentParser.Token.END_OBJECT)) {
    throw new ElasticsearchParseException("failed to parse [percentage] significance heuristic. expected an empty object, but got [{}] instead",parser.currentToken());
  }
  return new PercentageScore();
}
