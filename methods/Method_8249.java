public void updateTextureViewPosition(boolean needScroll){
  if (fragmentView == null || paused) {
    return;
  }
  boolean foundTextureViewMessage=false;
  int count=chatListView.getChildCount();
  for (int a=0; a < count; a++) {
    View view=chatListView.getChildAt(a);
    if (view instanceof ChatMessageCell) {
      ChatMessageCell messageCell=(ChatMessageCell)view;
      MessageObject messageObject=messageCell.getMessageObject();
      if (videoPlayerContainer != null && (messageObject.isRoundVideo() || messageObject.isVideo()) && MediaController.getInstance().isPlayingMessage(messageObject)) {
        ImageReceiver imageReceiver=messageCell.getPhotoImage();
        videoPlayerContainer.setTranslationX(imageReceiver.getImageX() + messageCell.getX());
        videoPlayerContainer.setTranslationY(fragmentView.getPaddingTop() + messageCell.getTop() + imageReceiver.getImageY() - chatListViewClipTop + chatListView.getTranslationY() + (inPreviewMode ? AndroidUtilities.statusBarHeight : 0));
        FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)videoPlayerContainer.getLayoutParams();
        if (messageObject.isRoundVideo()) {
          videoPlayerContainer.setTag(R.id.parent_tag,null);
          if (layoutParams.width != AndroidUtilities.roundMessageSize || layoutParams.height != AndroidUtilities.roundMessageSize) {
            layoutParams.width=layoutParams.height=AndroidUtilities.roundMessageSize;
            aspectRatioFrameLayout.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
            videoPlayerContainer.setLayoutParams(layoutParams);
          }
        }
 else {
          videoPlayerContainer.setTag(R.id.parent_tag,1);
          if (layoutParams.width != imageReceiver.getImageWidth() || layoutParams.height != imageReceiver.getImageHeight()) {
            aspectRatioFrameLayout.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
            layoutParams.width=imageReceiver.getImageWidth();
            layoutParams.height=imageReceiver.getImageHeight();
            videoPlayerContainer.setLayoutParams(layoutParams);
          }
        }
        fragmentView.invalidate();
        videoPlayerContainer.invalidate();
        foundTextureViewMessage=true;
        break;
      }
    }
  }
  if (needScroll && videoPlayerContainer != null) {
    MessageObject messageObject=MediaController.getInstance().getPlayingMessageObject();
    if (messageObject != null && messageObject.eventId == 0) {
      if (!foundTextureViewMessage) {
        if (checkTextureViewPosition && messageObject.isVideo()) {
          MediaController.getInstance().cleanupPlayer(true,true);
        }
 else {
          videoPlayerContainer.setTranslationY(-AndroidUtilities.roundMessageSize - 100);
          fragmentView.invalidate();
          if (messageObject != null && (messageObject.isRoundVideo() || messageObject.isVideo())) {
            if (checkTextureViewPosition || PipRoundVideoView.getInstance() != null) {
              MediaController.getInstance().setCurrentVideoVisible(false);
            }
 else {
              scrollToMessageId(messageObject.getId(),0,false,0,true);
            }
          }
        }
      }
 else {
        MediaController.getInstance().setCurrentVideoVisible(true);
        if (messageObject.isRoundVideo() || scrollToVideo) {
          scrollToMessageId(messageObject.getId(),0,false,0,true);
        }
 else {
          chatListView.invalidate();
        }
      }
    }
  }
}
