private boolean checkNeedDrawShareButton(MessageObject messageObject){
  if (currentPosition != null && !currentPosition.last) {
    return false;
  }
 else   if (messageObject.messageOwner.fwd_from != null && !messageObject.isOutOwner() && messageObject.messageOwner.fwd_from.saved_from_peer != null && messageObject.getDialogId() == UserConfig.getInstance(currentAccount).getClientUserId()) {
    drwaShareGoIcon=true;
  }
  return messageObject.needDrawShareButton();
}
