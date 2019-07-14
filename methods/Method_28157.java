@Override public boolean isRepoOwner(){
  if (getIssue() == null)   return false;
  Login me=Login.getUser();
  return TextUtils.equals(login,me.getLogin());
}
