@Override public SqlElasticDeleteByQueryRequestBuilder explain() throws SqlParseException {
  this.request=new DeleteByQueryRequestBuilder(client,DeleteByQueryAction.INSTANCE);
  setIndicesAndTypes();
  setWhere(delete.getWhere());
  if (delete.getRowCount() > -1) {
    request.size(delete.getRowCount());
  }
  updateRequestWithConflicts();
  SqlElasticDeleteByQueryRequestBuilder deleteByQueryRequestBuilder=new SqlElasticDeleteByQueryRequestBuilder(request);
  return deleteByQueryRequestBuilder;
}
