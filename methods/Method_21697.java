private ToXContent make(Condition cond,String name,SQLMethodInvokeExpr value) throws SqlParseException {
  ToXContent bqb=null;
  Paramer paramer=null;
switch (value.getMethodName().toLowerCase()) {
case "query":
    paramer=Paramer.parseParamer(value);
  QueryStringQueryBuilder queryString=QueryBuilders.queryStringQuery(paramer.value);
bqb=Paramer.fullParamer(queryString,paramer);
bqb=fixNot(cond,bqb);
break;
case "matchquery":
case "match_query":
paramer=Paramer.parseParamer(value);
MatchQueryBuilder matchQuery=QueryBuilders.matchQuery(name,paramer.value);
bqb=Paramer.fullParamer(matchQuery,paramer);
bqb=fixNot(cond,bqb);
break;
case "score":
case "scorequery":
case "score_query":
Float boost=Float.parseFloat(value.getParameters().get(1).toString());
Condition subCond=new Condition(cond.getConn(),cond.getName(),null,cond.getOpear(),value.getParameters().get(0),null);
bqb=QueryBuilders.constantScoreQuery((QueryBuilder)make(subCond)).boost(boost);
break;
case "wildcardquery":
case "wildcard_query":
paramer=Paramer.parseParamer(value);
WildcardQueryBuilder wildcardQuery=QueryBuilders.wildcardQuery(name,paramer.value);
bqb=Paramer.fullParamer(wildcardQuery,paramer);
break;
case "matchphrasequery":
case "match_phrase":
case "matchphrase":
paramer=Paramer.parseParamer(value);
MatchPhraseQueryBuilder matchPhraseQuery=QueryBuilders.matchPhraseQuery(name,paramer.value);
bqb=Paramer.fullParamer(matchPhraseQuery,paramer);
break;
case "multimatchquery":
case "multi_match":
case "multimatch":
paramer=Paramer.parseParamer(value);
MultiMatchQueryBuilder multiMatchQuery=QueryBuilders.multiMatchQuery(paramer.value);
bqb=Paramer.fullParamer(multiMatchQuery,paramer);
break;
case "spannearquery":
case "span_near":
case "spannear":
paramer=Paramer.parseParamer(value);
List<SpanQueryBuilder> clauses=new ArrayList<>();
try (XContentParser parser=JsonXContent.jsonXContent.createParser(new NamedXContentRegistry(new SearchModule(Settings.EMPTY,true,Collections.emptyList()).getNamedXContents()),LoggingDeprecationHandler.INSTANCE,paramer.clauses)){
while (parser.nextToken() != XContentParser.Token.END_ARRAY) {
QueryBuilder query=SpanNearQueryBuilder.parseInnerQueryBuilder(parser);
if (!(query instanceof SpanQueryBuilder)) {
throw new ParsingException(parser.getTokenLocation(),"spanNear [clauses] must be of type span query");
}
clauses.add((SpanQueryBuilder)query);
}
}
 catch (IOException e) {
throw new SqlParseException("could not parse clauses: " + e.getMessage());
}
SpanNearQueryBuilder spanNearQuery=QueryBuilders.spanNearQuery(clauses.get(0),Optional.ofNullable(paramer.slop).orElse(SpanNearQueryBuilder.DEFAULT_SLOP));
for (int i=1; i < clauses.size(); ++i) {
spanNearQuery.addClause(clauses.get(i));
}
bqb=Paramer.fullParamer(spanNearQuery,paramer);
break;
default :
throw new SqlParseException("it did not support this query method " + value.getMethodName());
}
return bqb;
}
