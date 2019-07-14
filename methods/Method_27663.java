@Override public void onPinUnpinGist(){
  if (getGist() == null)   return;
  PinnedGists.pinUpin(getGist());
  sendToView(view -> view.onUpdatePinIcon(getGist()));
}
