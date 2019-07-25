public BoolQueryBuilder must(QueryBuilder query){
  mustClauses.add(query);
  return this;
}
