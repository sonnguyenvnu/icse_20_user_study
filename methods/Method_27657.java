@Override public boolean isOwner(){
  return getGist() != null && getGist().getOwner() != null && getGist().getOwner().getLogin().equals(Login.getUser().getLogin());
}
