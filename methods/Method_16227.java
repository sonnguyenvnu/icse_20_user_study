protected void trySyncUserRole(final String userId,final List<String> roleIdList){
  new HashSet<>(roleIdList).stream().map(roleId -> {
    UserRoleEntity roleEntity=entityFactory.newInstance(UserRoleEntity.class);
    roleEntity.setRoleId(roleId);
    roleEntity.setUserId(userId);
    return roleEntity;
  }
).forEach(userRoleDao::insert);
}
