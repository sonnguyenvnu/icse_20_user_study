@Override public void onPinUnpinIssue(){
  if (getIssue() == null)   return;
  PinnedIssues.pinUpin(getIssue());
  sendToView(IssuePagerMvp.View::onUpdateMenu);
}
