@Override public void onItemClick(int position,View v,PullRequest item){
  PullsIssuesParser parser=PullsIssuesParser.getForPullRequest(item.getHtmlUrl());
  if (parser != null && getView() != null) {
    getView().onOpenPullRequest(parser);
  }
}
