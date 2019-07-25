public static SerialDiffPipelineAggregationBuilder parse(String reducerName,XContentParser parser) throws IOException {
  XContentParser.Token token;
  String currentFieldName=null;
  String[] bucketsPaths=null;
  String format=null;
  GapPolicy gapPolicy=null;
  Integer lag=null;
  while ((token=parser.nextToken()) != XContentParser.Token.END_OBJECT) {
    if (token == XContentParser.Token.FIELD_NAME) {
      currentFieldName=parser.currentName();
    }
 else     if (token == XContentParser.Token.VALUE_STRING) {
      if (FORMAT.match(currentFieldName)) {
        format=parser.text();
      }
 else       if (BUCKETS_PATH.match(currentFieldName)) {
        bucketsPaths=new String[]{parser.text()};
      }
 else       if (GAP_POLICY.match(currentFieldName)) {
        gapPolicy=GapPolicy.parse(parser.text(),parser.getTokenLocation());
      }
 else {
        throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + reducerName + "]: [" + currentFieldName + "].");
      }
    }
 else     if (token == XContentParser.Token.VALUE_NUMBER) {
      if (LAG.match(currentFieldName)) {
        lag=parser.intValue(true);
        if (lag <= 0) {
          throw new ParsingException(parser.getTokenLocation(),"Lag must be a positive, non-zero integer.  Value supplied was" + lag + " in [" + reducerName + "]: [" + currentFieldName + "].");
        }
      }
 else {
        throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + reducerName + "]: [" + currentFieldName + "].");
      }
    }
 else     if (token == XContentParser.Token.START_ARRAY) {
      if (BUCKETS_PATH.match(currentFieldName)) {
        List<String> paths=new ArrayList<>();
        while ((token=parser.nextToken()) != XContentParser.Token.END_ARRAY) {
          String path=parser.text();
          paths.add(path);
        }
        bucketsPaths=paths.toArray(new String[paths.size()]);
      }
 else {
        throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + reducerName + "]: [" + currentFieldName + "].");
      }
    }
 else {
      throw new ParsingException(parser.getTokenLocation(),"Unexpected token " + token + " in [" + reducerName + "].",parser.getTokenLocation());
    }
  }
  if (bucketsPaths == null) {
    throw new ParsingException(parser.getTokenLocation(),"Missing required field [" + BUCKETS_PATH.getPreferredName() + "] for derivative aggregation [" + reducerName + "]");
  }
  SerialDiffPipelineAggregationBuilder factory=new SerialDiffPipelineAggregationBuilder(reducerName,bucketsPaths[0]);
  if (lag != null) {
    factory.lag(lag);
  }
  if (format != null) {
    factory.format(format);
  }
  if (gapPolicy != null) {
    factory.gapPolicy(gapPolicy);
  }
  return factory;
}
