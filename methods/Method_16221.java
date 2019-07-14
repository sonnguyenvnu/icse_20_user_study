@Override public UserEntity createEntity(){
  return entityFactory.newInstance(BindRoleUserEntity.class);
}
