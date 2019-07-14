public void cleanup(){
  delayedMessages.clear();
  unsentMessages.clear();
  sendingMessages.clear();
  waitingForLocation.clear();
  waitingForCallback.clear();
  waitingForVote.clear();
  currentChatInfo=null;
  locationProvider.stop();
}
