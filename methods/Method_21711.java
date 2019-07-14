protected SearchRequestBuilder createRequestBuilder(Select select) throws SqlParseException {
  DefaultQueryAction queryAction=new DefaultQueryAction(client,select);
  queryAction.explain();
  return queryAction.getRequestBuilder();
}
