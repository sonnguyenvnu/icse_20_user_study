public static BucketScriptPipelineAggregationBuilder parse(String reducerName,XContentParser parser) throws IOException {
  XContentParser.Token token;
  Script script=null;
  String currentFieldName=null;
  Map<String,String> bucketsPathsMap=null;
  String format=null;
  GapPolicy gapPolicy=null;
  while ((token=parser.nextToken()) != XContentParser.Token.END_OBJECT) {
    if (token == XContentParser.Token.FIELD_NAME) {
      currentFieldName=parser.currentName();
    }
 else     if (token == XContentParser.Token.VALUE_STRING) {
      if (FORMAT.match(currentFieldName)) {
        format=parser.text();
      }
 else       if (BUCKETS_PATH.match(currentFieldName)) {
        bucketsPathsMap=new HashMap<>();
        bucketsPathsMap.put("_value",parser.text());
      }
 else       if (GAP_POLICY.match(currentFieldName)) {
        gapPolicy=GapPolicy.parse(parser.text(),parser.getTokenLocation());
      }
 else       if (Script.SCRIPT_PARSE_FIELD.match(currentFieldName)) {
        script=Script.parse(parser);
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
        bucketsPathsMap=new HashMap<>();
        for (int i=0; i < paths.size(); i++) {
          bucketsPathsMap.put("_value" + i,paths.get(i));
        }
      }
 else {
        throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + reducerName + "]: [" + currentFieldName + "].");
      }
    }
 else     if (token == XContentParser.Token.START_OBJECT) {
      if (Script.SCRIPT_PARSE_FIELD.match(currentFieldName)) {
        script=Script.parse(parser);
      }
 else       if (BUCKETS_PATH.match(currentFieldName)) {
        Map<String,Object> map=parser.map();
        bucketsPathsMap=new HashMap<>();
        for (        Map.Entry<String,Object> entry : map.entrySet()) {
          bucketsPathsMap.put(entry.getKey(),String.valueOf(entry.getValue()));
        }
      }
 else {
        throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + reducerName + "]: [" + currentFieldName + "].");
      }
    }
 else {
      throw new ParsingException(parser.getTokenLocation(),"Unexpected token " + token + " in [" + reducerName + "].");
    }
  }
  if (bucketsPathsMap == null) {
    throw new ParsingException(parser.getTokenLocation(),"Missing required field [" + BUCKETS_PATH.getPreferredName() + "] for series_arithmetic aggregation [" + reducerName + "]");
  }
  if (script == null) {
    throw new ParsingException(parser.getTokenLocation(),"Missing required field [" + Script.SCRIPT_PARSE_FIELD.getPreferredName() + "] for series_arithmetic aggregation [" + reducerName + "]");
  }
  BucketScriptPipelineAggregationBuilder factory=new BucketScriptPipelineAggregationBuilder(reducerName,bucketsPathsMap,script);
  if (format != null) {
    factory.format(format);
  }
  if (gapPolicy != null) {
    factory.gapPolicy(gapPolicy);
  }
  return factory;
}
