@Override public List<Group> findGroupsByUser(final String id){
  if (id == null) {
    return null;
  }
  try {
    List<RoleEntity> sysRoles=userService.getUserRole(id);
    return ActivitiUserUtil.toActivitiGroups(sysRoles);
  }
 catch (  EmptyResultDataAccessException e) {
    return new ArrayList<>();
  }
}
