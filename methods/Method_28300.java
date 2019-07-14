@Override public void onUpdateHeader(){
  if (getPullRequest() == null)   return;
  onSetHeader(new TimelineModel(getPullRequest()));
}
