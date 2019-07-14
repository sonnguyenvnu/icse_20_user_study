@Override public List<UserEntity> selectByUserByRole(List<String> roleIdList){
  if (CollectionUtils.isEmpty(roleIdList)) {
    return new java.util.ArrayList<>();
  }
  return createQuery().where("id","user-in-role",roleIdList).listNoPaging();
}
