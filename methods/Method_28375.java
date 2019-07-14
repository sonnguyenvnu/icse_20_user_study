@Override public void onPinUnpinRepo(){
  if (getRepo() == null)   return;
  boolean isPinned=AbstractPinnedRepos.pinUpin(getRepo());
  sendToView(view -> view.onRepoPinned(isPinned));
}
