public BoolQueryBuilder should(QueryBuilder query){
  shouldClauses.add(query);
  return this;
}
