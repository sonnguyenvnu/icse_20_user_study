private static void executeAndFillSubQuery(Client client,SubQueryExpression subQueryExpression,QueryAction queryAction) throws SqlParseException {
  List<Object> values=new ArrayList<>();
  Object queryResult;
  try {
    queryResult=QueryActionElasticExecutor.executeAnyAction(client,queryAction);
  }
 catch (  Exception e) {
    throw new SqlParseException("could not execute SubQuery: " + e.getMessage());
  }
  String returnField=subQueryExpression.getReturnField();
  if (queryResult instanceof SearchHits) {
    SearchHits hits=(SearchHits)queryResult;
    for (    SearchHit hit : hits) {
      values.add(ElasticResultHandler.getFieldValue(hit,returnField));
    }
  }
 else   if (queryResult instanceof SearchResponse) {
    SearchHits hits=((SearchResponse)queryResult).getHits();
    for (    SearchHit hit : hits) {
      values.add(ElasticResultHandler.getFieldValue(hit,returnField));
    }
  }
 else {
    throw new SqlParseException("on sub queries only support queries that return Hits and not aggregations");
  }
  subQueryExpression.setValues(values.toArray());
}
