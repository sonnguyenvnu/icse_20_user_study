@Override public <T>void delete(CriteriaQuery criteriaQuery,Class<T> clazz){
  QueryBuilder elasticsearchQuery=new CriteriaQueryProcessor().createQueryFromCriteria(criteriaQuery.getCriteria());
  Assert.notNull(elasticsearchQuery,"Query can not be null.");
  DeleteQuery deleteQuery=new DeleteQuery();
  deleteQuery.setQuery(elasticsearchQuery);
  delete(deleteQuery,clazz);
}
