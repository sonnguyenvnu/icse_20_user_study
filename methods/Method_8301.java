public boolean maybePlayVisibleVideo(){
  if (chatListView == null) {
    return false;
  }
  MessageObject playingMessage=MediaController.getInstance().getPlayingMessageObject();
  if (playingMessage != null && !playingMessage.isVideo()) {
    return false;
  }
  MessageObject visibleMessage=null;
  AnimatedFileDrawable visibleAnimation=null;
  if (noSoundHintView != null && noSoundHintView.getTag() != null) {
    ChatMessageCell cell=noSoundHintView.getMessageCell();
    ImageReceiver imageReceiver=cell.getPhotoImage();
    visibleAnimation=imageReceiver.getAnimation();
    if (visibleAnimation != null) {
      visibleMessage=cell.getMessageObject();
      scrollToVideo=cell.getTop() + imageReceiver.getImageY2() > chatListView.getMeasuredHeight();
    }
  }
  if (visibleMessage == null) {
    int count=chatListView.getChildCount();
    for (int a=0; a < count; a++) {
      View child=chatListView.getChildAt(a);
      if (!(child instanceof ChatMessageCell)) {
        continue;
      }
      ChatMessageCell messageCell=(ChatMessageCell)child;
      MessageObject messageObject=messageCell.getMessageObject();
      boolean isRoundVideo=messageObject.isRoundVideo();
      if (!messageObject.isVideo() && !isRoundVideo) {
        continue;
      }
      ImageReceiver imageReceiver=messageCell.getPhotoImage();
      AnimatedFileDrawable animation=imageReceiver.getAnimation();
      if (animation == null) {
        continue;
      }
      int top=child.getTop() + imageReceiver.getImageY();
      int bottom=top + imageReceiver.getImageHeight();
      if (bottom < 0 || top > chatListView.getMeasuredHeight()) {
        continue;
      }
      if (visibleMessage != null && top < 0) {
        break;
      }
      visibleMessage=messageObject;
      visibleAnimation=animation;
      scrollToVideo=top < 0 || bottom > chatListView.getMeasuredHeight();
      if (top >= 0 && bottom <= chatListView.getMeasuredHeight()) {
        break;
      }
    }
  }
  if (visibleMessage != null) {
    if (MediaController.getInstance().isPlayingMessage(visibleMessage)) {
      return false;
    }
    if (noSoundHintView != null) {
      noSoundHintView.hide();
    }
    if (forwardHintView != null) {
      forwardHintView.hide();
    }
    if (visibleMessage.isRoundVideo()) {
      boolean result=MediaController.getInstance().playMessage(visibleMessage);
      MediaController.getInstance().setVoiceMessagesPlaylist(result ? createVoiceMessagesPlaylist(visibleMessage,false) : null,false);
      return result;
    }
 else {
      SharedConfig.setNoSoundHintShowed(true);
      visibleMessage.audioProgress=visibleAnimation.getCurrentProgress();
      visibleMessage.audioProgressMs=visibleAnimation.getCurrentProgressMs();
      visibleAnimation.stop();
      if (PhotoViewer.isPlayingMessageInPip(visibleMessage)) {
        PhotoViewer.getPipInstance().destroyPhotoViewer();
      }
      return MediaController.getInstance().playMessage(visibleMessage);
    }
  }
  return false;
}
