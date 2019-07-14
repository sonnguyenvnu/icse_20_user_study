private void checkIndexName(String indexName){
  Preconditions.checkArgument(StringUtils.isNotBlank(indexName));
  Preconditions.checkArgument(getGraphIndex(indexName) == null,"An index with name '%s' has already been defined",indexName);
}
