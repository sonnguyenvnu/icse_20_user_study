public int getSecretTimeLeft(){
  int secondsLeft=messageOwner.ttl;
  if (messageOwner.destroyTime != 0) {
    secondsLeft=Math.max(0,messageOwner.destroyTime - ConnectionsManager.getInstance(currentAccount).getCurrentTime());
  }
  return secondsLeft;
}
