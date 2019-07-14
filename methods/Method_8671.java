private void checkVisibility(){
  boolean show=false;
  if (isLocation) {
    if (fragment instanceof DialogsActivity) {
      show=LocationController.getLocationsCount() != 0;
    }
 else {
      show=LocationController.getInstance(fragment.getCurrentAccount()).isSharingLocation(((ChatActivity)fragment).getDialogId());
    }
  }
 else {
    if (VoIPService.getSharedInstance() != null && VoIPService.getSharedInstance().getCallState() != VoIPService.STATE_WAITING_INCOMING) {
      show=true;
    }
 else {
      MessageObject messageObject=MediaController.getInstance().getPlayingMessageObject();
      if (messageObject != null && messageObject.getId() != 0) {
        show=true;
      }
    }
  }
  setVisibility(show ? VISIBLE : GONE);
}
