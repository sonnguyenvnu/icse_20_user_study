public void update(Group group){
  getGroupRole(group);
  for (  Role r : group.getRoles()) {
    groupRoleDAO.delete(new GroupRole(group.getId(),r.getId()));
  }
  updatetWithTxCallback(group);
}
