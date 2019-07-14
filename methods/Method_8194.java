private void updateMessagesVisisblePart(){
  if (chatListView == null) {
    return;
  }
  int count=chatListView.getChildCount();
  int height=chatListView.getMeasuredHeight();
  int minPositionHolder=Integer.MAX_VALUE;
  int minPositionDateHolder=Integer.MAX_VALUE;
  View minDateChild=null;
  View minChild=null;
  View minMessageChild=null;
  boolean foundTextureViewMessage=false;
  for (int a=0; a < count; a++) {
    View view=chatListView.getChildAt(a);
    if (view instanceof ChatMessageCell) {
      ChatMessageCell messageCell=(ChatMessageCell)view;
      int top=messageCell.getTop();
      int bottom=messageCell.getBottom();
      int viewTop=top >= 0 ? 0 : -top;
      int viewBottom=messageCell.getMeasuredHeight();
      if (viewBottom > height) {
        viewBottom=viewTop + height;
      }
      messageCell.setVisiblePart(viewTop,viewBottom - viewTop);
      MessageObject messageObject=messageCell.getMessageObject();
      if (roundVideoContainer != null && messageObject.isRoundVideo() && MediaController.getInstance().isPlayingMessage(messageObject)) {
        ImageReceiver imageReceiver=messageCell.getPhotoImage();
        roundVideoContainer.setTranslationX(imageReceiver.getImageX());
        roundVideoContainer.setTranslationY(fragmentView.getPaddingTop() + top + imageReceiver.getImageY());
        fragmentView.invalidate();
        roundVideoContainer.invalidate();
        foundTextureViewMessage=true;
      }
    }
    if (view.getBottom() <= chatListView.getPaddingTop()) {
      continue;
    }
    int position=view.getBottom();
    if (position < minPositionHolder) {
      minPositionHolder=position;
      if (view instanceof ChatMessageCell || view instanceof ChatActionCell) {
        minMessageChild=view;
      }
      minChild=view;
    }
    if (view instanceof ChatActionCell && ((ChatActionCell)view).getMessageObject().isDateObject) {
      if (view.getAlpha() != 1.0f) {
        view.setAlpha(1.0f);
      }
      if (position < minPositionDateHolder) {
        minPositionDateHolder=position;
        minDateChild=view;
      }
    }
  }
  if (roundVideoContainer != null) {
    if (!foundTextureViewMessage) {
      roundVideoContainer.setTranslationY(-AndroidUtilities.roundMessageSize - 100);
      fragmentView.invalidate();
      MessageObject messageObject=MediaController.getInstance().getPlayingMessageObject();
      if (messageObject != null && messageObject.isRoundVideo() && checkTextureViewPosition) {
        MediaController.getInstance().setCurrentVideoVisible(false);
      }
    }
 else {
      MediaController.getInstance().setCurrentVideoVisible(true);
    }
  }
  if (minMessageChild != null) {
    MessageObject messageObject;
    if (minMessageChild instanceof ChatMessageCell) {
      messageObject=((ChatMessageCell)minMessageChild).getMessageObject();
    }
 else {
      messageObject=((ChatActionCell)minMessageChild).getMessageObject();
    }
    floatingDateView.setCustomDate(messageObject.messageOwner.date);
  }
  currentFloatingDateOnScreen=false;
  currentFloatingTopIsNotMessage=!(minChild instanceof ChatMessageCell || minChild instanceof ChatActionCell);
  if (minDateChild != null) {
    if (minDateChild.getTop() > chatListView.getPaddingTop() || currentFloatingTopIsNotMessage) {
      if (minDateChild.getAlpha() != 1.0f) {
        minDateChild.setAlpha(1.0f);
      }
      hideFloatingDateView(!currentFloatingTopIsNotMessage);
    }
 else {
      if (minDateChild.getAlpha() != 0.0f) {
        minDateChild.setAlpha(0.0f);
      }
      if (floatingDateAnimation != null) {
        floatingDateAnimation.cancel();
        floatingDateAnimation=null;
      }
      if (floatingDateView.getTag() == null) {
        floatingDateView.setTag(1);
      }
      if (floatingDateView.getAlpha() != 1.0f) {
        floatingDateView.setAlpha(1.0f);
      }
      currentFloatingDateOnScreen=true;
    }
    int offset=minDateChild.getBottom() - chatListView.getPaddingTop();
    if (offset > floatingDateView.getMeasuredHeight() && offset < floatingDateView.getMeasuredHeight() * 2) {
      floatingDateView.setTranslationY(-floatingDateView.getMeasuredHeight() * 2 + offset);
    }
 else {
      floatingDateView.setTranslationY(0);
    }
  }
 else {
    hideFloatingDateView(true);
    floatingDateView.setTranslationY(0);
  }
}
