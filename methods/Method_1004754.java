public BoolQueryBuilder filters(Collection<QueryBuilder> filters){
  filterClauses.addAll(filters);
  return this;
}
