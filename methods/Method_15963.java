@Override default List<E> select(){
  return createRequest("/all").get().asList(getEntityType());
}
