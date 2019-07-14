private void checkRaiseSensors(){
  if (chatActivityEnterView != null && chatActivityEnterView.isStickersExpanded()) {
    MediaController.getInstance().setAllowStartRecord(false);
  }
 else   if (currentChat != null && !ChatObject.canSendMedia(currentChat)) {
    MediaController.getInstance().setAllowStartRecord(false);
  }
 else   if (!ApplicationLoader.mainInterfacePaused && (bottomOverlayChat == null || bottomOverlayChat.getVisibility() != View.VISIBLE) && (bottomOverlay == null || bottomOverlay.getVisibility() != View.VISIBLE) && (searchContainer == null || searchContainer.getVisibility() != View.VISIBLE)) {
    MediaController.getInstance().setAllowStartRecord(true);
  }
 else {
    MediaController.getInstance().setAllowStartRecord(false);
  }
}
