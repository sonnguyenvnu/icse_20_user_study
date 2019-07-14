@Override public void dismissCurrentDialig(){
  if (chatAttachAlert != null && visibleDialog == chatAttachAlert) {
    chatAttachAlert.closeCamera(false);
    chatAttachAlert.dismissInternal();
    chatAttachAlert.hideCamera(true);
    return;
  }
  super.dismissCurrentDialig();
}
