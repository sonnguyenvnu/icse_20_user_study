private void updateMessagesVisiblePart(boolean inLayout){
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
  int maxPositiveUnreadId=Integer.MIN_VALUE;
  int maxNegativeUnreadId=Integer.MAX_VALUE;
  int maxUnreadDate=Integer.MIN_VALUE;
  int lastVisibleId=currentEncryptedChat != null ? Integer.MAX_VALUE : Integer.MIN_VALUE;
  pollsToCheck.clear();
  for (int a=0; a < count; a++) {
    View view=chatListView.getChildAt(a);
    MessageObject messageObject=null;
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
      messageObject=messageCell.getMessageObject();
      boolean isVideo;
      if (videoPlayerContainer != null && (isVideo=messageObject.isVideo() || messageObject.isRoundVideo()) && MediaController.getInstance().isPlayingMessage(messageObject)) {
        ImageReceiver imageReceiver=messageCell.getPhotoImage();
        if (isVideo && top + imageReceiver.getImageY2() < 0) {
          foundTextureViewMessage=false;
        }
 else {
          videoPlayerContainer.setTranslationX(imageReceiver.getImageX() + messageCell.getX());
          videoPlayerContainer.setTranslationY(fragmentView.getPaddingTop() + top + imageReceiver.getImageY() - chatListViewClipTop + chatListView.getTranslationY() + (inPreviewMode ? AndroidUtilities.statusBarHeight : 0));
          fragmentView.invalidate();
          videoPlayerContainer.invalidate();
          foundTextureViewMessage=true;
        }
      }
    }
 else     if (view instanceof ChatActionCell) {
      messageObject=((ChatActionCell)view).getMessageObject();
    }
    if (messageObject != null) {
      if (!messageObject.isOut() && messageObject.isUnread()) {
        int id=messageObject.getId();
        if (id > 0) {
          maxPositiveUnreadId=Math.max(maxPositiveUnreadId,messageObject.getId());
        }
        if (id < 0) {
          maxNegativeUnreadId=Math.min(maxNegativeUnreadId,messageObject.getId());
        }
        maxUnreadDate=Math.max(maxUnreadDate,messageObject.messageOwner.date);
      }
      if (messageObject.type == MessageObject.TYPE_POLL) {
        pollsToCheck.add(messageObject);
      }
    }
    if (view.getBottom() <= chatListView.getPaddingTop() + AndroidUtilities.dp(1) + chatListViewClipTop) {
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
    if (view instanceof ChatActionCell && messageObject.isDateObject) {
      if (view.getAlpha() != 1.0f) {
        view.setAlpha(1.0f);
      }
      if (position < minPositionDateHolder) {
        minPositionDateHolder=position;
        minDateChild=view;
      }
    }
  }
  MessagesController.getInstance(currentAccount).addToPollsQueue(dialog_id,pollsToCheck);
  if (videoPlayerContainer != null) {
    if (!foundTextureViewMessage) {
      MessageObject messageObject=MediaController.getInstance().getPlayingMessageObject();
      if (messageObject != null) {
        if (checkTextureViewPosition && messageObject.isVideo()) {
          MediaController.getInstance().cleanupPlayer(true,true);
        }
 else {
          videoPlayerContainer.setTranslationY(-AndroidUtilities.roundMessageSize - 100);
          fragmentView.invalidate();
          if ((messageObject.isRoundVideo() || messageObject.isVideo()) && messageObject.eventId == 0 && checkTextureViewPosition) {
            MediaController.getInstance().setCurrentVideoVisible(false);
          }
        }
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
    if (minDateChild.getTop() - chatListViewClipTop > chatListView.getPaddingTop() || currentFloatingTopIsNotMessage) {
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
    float offset=minDateChild.getBottom() - chatListView.getPaddingTop() - chatListViewClipTop;
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
  if (!firstLoading && !paused && !inPreviewMode) {
    if ((maxPositiveUnreadId != Integer.MIN_VALUE || maxNegativeUnreadId != Integer.MAX_VALUE)) {
      int counterDicrement=0;
      for (int a=0; a < messages.size(); a++) {
        MessageObject messageObject=messages.get(a);
        int id=messageObject.getId();
        if (maxPositiveUnreadId != Integer.MIN_VALUE) {
          if (id > 0 && id <= maxPositiveUnreadId && messageObject.isUnread()) {
            messageObject.setIsRead();
            counterDicrement++;
          }
        }
        if (maxNegativeUnreadId != Integer.MAX_VALUE) {
          if (id < 0 && id >= maxNegativeUnreadId && messageObject.isUnread()) {
            messageObject.setIsRead();
            counterDicrement++;
          }
        }
      }
      if (forwardEndReached[0] && maxPositiveUnreadId == minMessageId[0] || maxNegativeUnreadId == minMessageId[0]) {
        newUnreadMessageCount=0;
      }
 else {
        newUnreadMessageCount-=counterDicrement;
        if (newUnreadMessageCount < 0) {
          newUnreadMessageCount=0;
        }
      }
      if (inLayout) {
        AndroidUtilities.runOnUIThread(this::inlineUpdate1);
      }
 else {
        inlineUpdate1();
      }
      MessagesController.getInstance(currentAccount).markDialogAsRead(dialog_id,maxPositiveUnreadId,maxNegativeUnreadId,maxUnreadDate,false,counterDicrement,maxPositiveUnreadId == minMessageId[0] || maxNegativeUnreadId == minMessageId[0]);
      firstUnreadSent=true;
    }
 else     if (!firstUnreadSent) {
      if (chatLayoutManager.findFirstVisibleItemPosition() == 0) {
        newUnreadMessageCount=0;
        if (inLayout) {
          AndroidUtilities.runOnUIThread(this::inlineUpdate2);
        }
 else {
          inlineUpdate2();
        }
        MessagesController.getInstance(currentAccount).markDialogAsRead(dialog_id,minMessageId[0],minMessageId[0],maxDate[0],false,0,true);
        firstUnreadSent=true;
      }
    }
  }
}
