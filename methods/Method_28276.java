@Override public void onPinUnpinPullRequest(){
  if (getPullRequest() == null)   return;
  PinnedPullRequests.pinUpin(getPullRequest());
  sendToView(PullRequestPagerMvp.View::onUpdateMenu);
}
