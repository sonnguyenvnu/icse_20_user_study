@Override @Transactional(readOnly=true) public UserEntity selectByPk(String id){
  if (!StringUtils.hasLength(id)) {
    return null;
  }
  UserEntity userEntity=createQuery().where(UserEntity.id,id).single();
  if (null != userEntity) {
    List<String> roleId=userRoleDao.selectByUserId(id).stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    BindRoleUserEntity roleUserEntity=entityFactory.newInstance(BindRoleUserEntity.class,userEntity);
    roleUserEntity.setRoles(roleId);
    return roleUserEntity;
  }
  return null;
}
