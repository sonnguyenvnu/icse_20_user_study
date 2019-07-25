@Override public List<JSONObject> select(final String statement,final Object... params) throws RepositoryException {
  JSONArray jsonResults;
  final Connection connection=getConnection();
  try {
    if (null == params || 0 == params.length) {
      jsonResults=JdbcUtil.queryJsonArray(statement,Collections.emptyList(),connection,getName(),debug);
    }
 else {
      jsonResults=JdbcUtil.queryJsonArray(statement,Arrays.asList(params),connection,getName(),debug);
    }
    return CollectionUtils.jsonArrayToList(jsonResults);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Select failed",e);
    throw new RepositoryException(e);
  }
}
