@Override public boolean isOwner(){
  if (getPullRequest() == null)   return false;
  User userModel=getPullRequest() != null ? getPullRequest().getUser() : null;
  Login me=Login.getUser();
  PullsIssuesParser parser=PullsIssuesParser.getForIssue(getPullRequest().getHtmlUrl());
  return (userModel != null && userModel.getLogin().equalsIgnoreCase(me.getLogin())) || (parser != null && parser.getLogin().equalsIgnoreCase(me.getLogin()));
}
