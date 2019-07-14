@Override public void onUpdateHeader(){
  if (getIssue() == null)   return;
  onSetHeader(TimelineModel.constructHeader(getIssue()));
}
