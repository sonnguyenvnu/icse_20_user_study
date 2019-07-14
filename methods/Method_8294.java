@Override protected void setInPreviewMode(boolean value){
  super.setInPreviewMode(value);
  if (avatarContainer != null) {
    avatarContainer.setOccupyStatusBar(!value);
    avatarContainer.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT,!value ? 56 : 0,0,40,0));
  }
  if (chatActivityEnterView != null) {
    chatActivityEnterView.setVisibility(!value ? View.VISIBLE : View.INVISIBLE);
  }
  if (actionBar != null) {
    actionBar.setBackButtonDrawable(!value ? new BackDrawable(false) : null);
    headerItem.setAlpha(!value ? 1.0f : 0.0f);
    attachItem.setAlpha(!value ? 1.0f : 0.0f);
  }
  if (chatListView != null) {
    int count=chatListView.getChildCount();
    for (int a=0; a < count; a++) {
      View view=chatListView.getChildAt(a);
      MessageObject message=null;
      if (view instanceof ChatMessageCell) {
        message=((ChatMessageCell)view).getMessageObject();
      }
 else       if (view instanceof ChatActionCell) {
        message=((ChatActionCell)view).getMessageObject();
      }
      if (message != null && message.messageOwner != null && message.messageOwner.media_unread && message.messageOwner.mentioned) {
        if (!message.isVoice() && !message.isRoundVideo()) {
          newMentionsCount--;
          if (newMentionsCount <= 0) {
            newMentionsCount=0;
            hasAllMentionsLocal=true;
            showMentionDownButton(false,true);
          }
 else {
            mentiondownButtonCounter.setText(String.format("%d",newMentionsCount));
          }
          MessagesController.getInstance(currentAccount).markMentionMessageAsRead(message.getId(),ChatObject.isChannel(currentChat) ? currentChat.id : 0,dialog_id);
          message.setContentIsRead();
        }
        if (view instanceof ChatMessageCell) {
          ((ChatMessageCell)view).setHighlighted(false);
          ((ChatMessageCell)view).setHighlightedAnimated();
        }
      }
    }
  }
  updateBottomOverlay();
  updateSecretStatus();
}
