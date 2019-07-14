private void removeUnreadPlane(boolean scrollToEnd){
  if (unreadMessageObject != null) {
    if (scrollToEnd) {
      forwardEndReached[0]=forwardEndReached[1]=true;
      first_unread_id=0;
      last_message_id=0;
    }
    createUnreadMessageAfterId=0;
    createUnreadMessageAfterIdLoading=false;
    removeMessageObject(unreadMessageObject);
    unreadMessageObject=null;
  }
}
