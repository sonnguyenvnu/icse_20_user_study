@Override default List<E> select(Entity param){
  return createRequest("/no-paging",param).get().asList(getEntityType());
}
