private ObjectResult getObjectResult(boolean flat,String query,boolean includeScore,boolean includeType,boolean includeId) throws SqlParseException, SQLFeatureNotSupportedException, Exception, CsvExtractorException {
  SearchDao searchDao=new org.nlpcn.es4sql.SearchDao(client);
  QueryAction queryAction=searchDao.explain(query);
  Object execution=QueryActionElasticExecutor.executeAnyAction(searchDao.getClient(),queryAction);
  return new ObjectResultsExtractor(includeScore,includeType,includeId,false,queryAction).extractResults(execution,flat);
}
