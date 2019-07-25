public void update(Role role){
  getRolTasks(role);
  for (  Task t : role.getTasks()) {
    roleTaskDAO.delete(new RoleTask(role.getId(),t.getId()));
  }
  updatetWithTxCallback(role);
}
