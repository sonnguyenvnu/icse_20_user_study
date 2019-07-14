@Override @Transactional(readOnly=true) public List<E> selectByPk(List<PK> id){
  if (CollectionUtils.isEmpty(id)) {
    return new ArrayList<>();
  }
  return createQuery().where().in(GenericEntity.id,id).listNoPaging();
}
