@Override public List<Group> findGroupsByUser(final String userId){
  if (userId == null) {
    return new java.util.ArrayList<>();
  }
  List<RoleEntity> sysRoles=userService.getUserRole(userId);
  return ActivitiUserUtil.toActivitiGroups(sysRoles);
}
