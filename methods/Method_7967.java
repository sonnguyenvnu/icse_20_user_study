private boolean isCurrentLocationTimeExpired(MessageObject messageObject){
  if (currentMessageObject.messageOwner.media.period % 60 == 0) {
    return Math.abs(ConnectionsManager.getInstance(currentAccount).getCurrentTime() - messageObject.messageOwner.date) > messageObject.messageOwner.media.period;
  }
 else {
    return Math.abs(ConnectionsManager.getInstance(currentAccount).getCurrentTime() - messageObject.messageOwner.date) > messageObject.messageOwner.media.period - 5;
  }
}
