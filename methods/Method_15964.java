@Override default List<E> selectByPk(List<PK> id){
  return createRequest("/ids").param("ids",id.stream().map(String::valueOf).reduce((id1,id2) -> String.join(",",id1,id2)).orElse("")).get().asList(getEntityType());
}
