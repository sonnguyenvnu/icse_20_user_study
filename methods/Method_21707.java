@Override public SqlElasticRequestBuilder explain() throws SqlParseException {
  if (!isValidMultiSelectReturnFields()) {
    throw new SqlParseException("on multi query fields/aliases of one table should be subset of other");
  }
  MultiQueryRequestBuilder requestBuilder=new MultiQueryRequestBuilder(this.multiQuerySelect);
  requestBuilder.setFirstSearchRequest(createRequestBuilder(this.multiQuerySelect.getFirstSelect()));
  requestBuilder.setSecondSearchRequest(createRequestBuilder(this.multiQuerySelect.getSecondSelect()));
  requestBuilder.fillTableAliases(this.multiQuerySelect.getFirstSelect().getFields(),this.multiQuerySelect.getSecondSelect().getFields());
  return requestBuilder;
}
