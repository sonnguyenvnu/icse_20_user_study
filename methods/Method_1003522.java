@Override public Page<T> search(SearchQuery query){
  return elasticsearchOperations.queryForPage(query,getEntityClass());
}
