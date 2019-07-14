@Override public boolean isOwner(){
  if (getIssue() == null)   return false;
  User userModel=getIssue() != null ? getIssue().getUser() : null;
  Login me=Login.getUser();
  PullsIssuesParser parser=PullsIssuesParser.getForIssue(getIssue().getHtmlUrl());
  return (userModel != null && userModel.getLogin().equalsIgnoreCase(me.getLogin())) || (parser != null && parser.getLogin().equalsIgnoreCase(me.getLogin()));
}
