public <S extends T>List<S> save(List<S> entities){
  Assert.notNull(entities,"Cannot insert 'null' as a List.");
  Assert.notEmpty(entities,"Cannot insert empty List.");
  List<IndexQuery> queries=new ArrayList<>();
  for (  S s : entities) {
    queries.add(createIndexQuery(s));
  }
  elasticsearchOperations.bulkIndex(queries);
  elasticsearchOperations.refresh(entityInformation.getIndexName());
  return entities;
}
