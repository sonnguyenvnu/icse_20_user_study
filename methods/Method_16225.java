@Override public List<UserEntity> selectByPk(List<String> id){
  if (CollectionUtils.isEmpty(id)) {
    return new ArrayList<>();
  }
  return createQuery().where().in(UserEntity.id,id).listNoPaging();
}
