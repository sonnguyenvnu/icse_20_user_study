@Override public void onHandleReaction(int viewId,long id,@ReactionsProvider.ReactionType int reactionType){
  if (getView() == null || getView().getIssue() == null)   return;
  Issue issue=getView().getIssue();
  String login=issue.getLogin();
  String repoId=issue.getRepoId();
  Observable observable=getReactionsProvider().onHandleReaction(viewId,id,login,repoId,reactionType,isEnterprise());
  if (observable != null)   manageObservable(observable);
}
