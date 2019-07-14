@Override public boolean isRepoOwner(){
  if (getPullRequest() == null)   return false;
  Login me=Login.getUser();
  return TextUtils.equals(login,me.getLogin());
}
