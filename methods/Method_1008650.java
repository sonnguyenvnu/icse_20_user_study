public static TopHitsAggregationBuilder parse(String aggregationName,XContentParser parser) throws IOException {
  TopHitsAggregationBuilder factory=new TopHitsAggregationBuilder(aggregationName);
  XContentParser.Token token;
  String currentFieldName=null;
  while ((token=parser.nextToken()) != XContentParser.Token.END_OBJECT) {
    if (token == XContentParser.Token.FIELD_NAME) {
      currentFieldName=parser.currentName();
    }
 else     if (token.isValue()) {
      if (SearchSourceBuilder.FROM_FIELD.match(currentFieldName)) {
        factory.from(parser.intValue());
      }
 else       if (SearchSourceBuilder.SIZE_FIELD.match(currentFieldName)) {
        factory.size(parser.intValue());
      }
 else       if (SearchSourceBuilder.VERSION_FIELD.match(currentFieldName)) {
        factory.version(parser.booleanValue());
      }
 else       if (SearchSourceBuilder.EXPLAIN_FIELD.match(currentFieldName)) {
        factory.explain(parser.booleanValue());
      }
 else       if (SearchSourceBuilder.TRACK_SCORES_FIELD.match(currentFieldName)) {
        factory.trackScores(parser.booleanValue());
      }
 else       if (SearchSourceBuilder._SOURCE_FIELD.match(currentFieldName)) {
        factory.fetchSource(FetchSourceContext.fromXContent(parser));
      }
 else       if (SearchSourceBuilder.STORED_FIELDS_FIELD.match(currentFieldName)) {
        factory.storedFieldsContext=StoredFieldsContext.fromXContent(SearchSourceBuilder.STORED_FIELDS_FIELD.getPreferredName(),parser);
      }
 else       if (SearchSourceBuilder.SORT_FIELD.match(currentFieldName)) {
        factory.sort(parser.text());
      }
 else {
        throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + currentFieldName + "].",parser.getTokenLocation());
      }
    }
 else     if (token == XContentParser.Token.START_OBJECT) {
      if (SearchSourceBuilder._SOURCE_FIELD.match(currentFieldName)) {
        factory.fetchSource(FetchSourceContext.fromXContent(parser));
      }
 else       if (SearchSourceBuilder.SCRIPT_FIELDS_FIELD.match(currentFieldName)) {
        List<ScriptField> scriptFields=new ArrayList<>();
        while ((token=parser.nextToken()) != XContentParser.Token.END_OBJECT) {
          String scriptFieldName=parser.currentName();
          token=parser.nextToken();
          if (token == XContentParser.Token.START_OBJECT) {
            Script script=null;
            boolean ignoreFailure=false;
            while ((token=parser.nextToken()) != XContentParser.Token.END_OBJECT) {
              if (token == XContentParser.Token.FIELD_NAME) {
                currentFieldName=parser.currentName();
              }
 else               if (token.isValue()) {
                if (SearchSourceBuilder.SCRIPT_FIELD.match(currentFieldName)) {
                  script=Script.parse(parser);
                }
 else                 if (SearchSourceBuilder.IGNORE_FAILURE_FIELD.match(currentFieldName)) {
                  ignoreFailure=parser.booleanValue();
                }
 else {
                  throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + currentFieldName + "].",parser.getTokenLocation());
                }
              }
 else               if (token == XContentParser.Token.START_OBJECT) {
                if (SearchSourceBuilder.SCRIPT_FIELD.match(currentFieldName)) {
                  script=Script.parse(parser);
                }
 else {
                  throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + currentFieldName + "].",parser.getTokenLocation());
                }
              }
 else {
                throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + currentFieldName + "].",parser.getTokenLocation());
              }
            }
            scriptFields.add(new ScriptField(scriptFieldName,script,ignoreFailure));
          }
 else {
            throw new ParsingException(parser.getTokenLocation(),"Expected [" + XContentParser.Token.START_OBJECT + "] in [" + currentFieldName + "] but found [" + token + "]",parser.getTokenLocation());
          }
        }
        factory.scriptFields(scriptFields);
      }
 else       if (SearchSourceBuilder.HIGHLIGHT_FIELD.match(currentFieldName)) {
        factory.highlighter(HighlightBuilder.fromXContent(parser));
      }
 else       if (SearchSourceBuilder.SORT_FIELD.match(currentFieldName)) {
        List<SortBuilder<?>> sorts=SortBuilder.fromXContent(parser);
        factory.sorts(sorts);
      }
 else {
        throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + currentFieldName + "].",parser.getTokenLocation());
      }
    }
 else     if (token == XContentParser.Token.START_ARRAY) {
      if (SearchSourceBuilder.STORED_FIELDS_FIELD.match(currentFieldName)) {
        factory.storedFieldsContext=StoredFieldsContext.fromXContent(SearchSourceBuilder.STORED_FIELDS_FIELD.getPreferredName(),parser);
      }
 else       if (SearchSourceBuilder.DOCVALUE_FIELDS_FIELD.match(currentFieldName)) {
        List<String> fieldDataFields=new ArrayList<>();
        while ((token=parser.nextToken()) != XContentParser.Token.END_ARRAY) {
          if (token == XContentParser.Token.VALUE_STRING) {
            fieldDataFields.add(parser.text());
          }
 else {
            throw new ParsingException(parser.getTokenLocation(),"Expected [" + XContentParser.Token.VALUE_STRING + "] in [" + currentFieldName + "] but found [" + token + "]",parser.getTokenLocation());
          }
        }
        factory.fieldDataFields(fieldDataFields);
      }
 else       if (SearchSourceBuilder.SORT_FIELD.match(currentFieldName)) {
        List<SortBuilder<?>> sorts=SortBuilder.fromXContent(parser);
        factory.sorts(sorts);
      }
 else       if (SearchSourceBuilder._SOURCE_FIELD.match(currentFieldName)) {
        factory.fetchSource(FetchSourceContext.fromXContent(parser));
      }
 else {
        throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + currentFieldName + "].",parser.getTokenLocation());
      }
    }
 else {
      throw new ParsingException(parser.getTokenLocation(),"Unknown key for a " + token + " in [" + currentFieldName + "].",parser.getTokenLocation());
    }
  }
  return factory;
}
