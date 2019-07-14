@Override public List<RoleEntity> getUserRole(String userId){
  Assert.hasLength(userId,"??????");
  List<UserRoleEntity> roleEntities=userRoleDao.selectByUserId(userId);
  if (roleEntities.isEmpty()) {
    return new ArrayList<>();
  }
  List<String> roleIdList=roleEntities.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
  return DefaultDSLQueryService.createQuery(roleDao).where().in(GenericEntity.id,roleIdList).noPaging().list();
}
