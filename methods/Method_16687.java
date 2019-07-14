public GroupEntity findGroupById(final String id){
  if (id == null) {
    return null;
  }
  try {
    List<RoleEntity> sysRoles=userService.getUserRole(id);
    return ActivitiUserUtil.toActivitiGroup(sysRoles.get(0));
  }
 catch (  EmptyResultDataAccessException e) {
    return null;
  }
}
