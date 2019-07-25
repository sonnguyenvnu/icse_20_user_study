public void update(User user){
  User dbUser=userDAO.get(user.getId());
  getGroupUser(dbUser);
  getRoleUser(dbUser);
  if (dbUser.getGroups() != null && dbUser.getGroups().size() > 0) {
    for (    Group g : dbUser.getGroups()) {
      groupUserDAO.delete(new GroupUser(g.getId(),dbUser.getId()));
    }
  }
  if (dbUser.getRoles() != null && dbUser.getRoles().size() > 0) {
    for (    Role r : dbUser.getRoles()) {
      roleUserDAO.delete(new RoleUser(r.getId(),dbUser.getId()));
    }
  }
  updatetWithTxCallback(user);
}
