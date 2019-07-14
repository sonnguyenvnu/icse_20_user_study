@Override public boolean isRepoOwner(){
  if (getRepo() != null && getRepo().getOwner() != null) {
    return getRepo().getOwner().getLogin().equals(Login.getUser().getLogin()) || isCollaborator;
  }
  return false;
}
