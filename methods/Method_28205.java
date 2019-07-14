@Override public void onItemClick(int position,View v,Issue item){
  PullsIssuesParser parser=PullsIssuesParser.getForIssue(item.getHtmlUrl());
  if (parser != null && getView() != null) {
    getView().onOpenIssue(parser);
  }
}
