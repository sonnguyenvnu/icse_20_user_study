@Override protected void onRemoveFromParent(){
  MessageObject messageObject=MediaController.getInstance().getPlayingMessageObject();
  if (messageObject != null && messageObject.isVideo()) {
    MediaController.getInstance().cleanupPlayer(true,true);
  }
 else {
    MediaController.getInstance().setTextureView(videoTextureView,null,null,false);
  }
}
