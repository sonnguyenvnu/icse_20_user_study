public void checkVideoPlayback(boolean allowStart){
  if (currentMessageObject.isVideo()) {
    if (MediaController.getInstance().isPlayingMessage(currentMessageObject)) {
      photoImage.setAllowStartAnimation(false);
      photoImage.stopAnimation();
    }
 else {
      photoImage.setAllowStartAnimation(true);
      photoImage.startAnimation();
    }
  }
 else {
    if (allowStart) {
      MessageObject playingMessage=MediaController.getInstance().getPlayingMessageObject();
      allowStart=playingMessage == null || !playingMessage.isRoundVideo();
    }
    photoImage.setAllowStartAnimation(allowStart);
    if (allowStart) {
      photoImage.startAnimation();
    }
 else {
      photoImage.stopAnimation();
    }
  }
}
