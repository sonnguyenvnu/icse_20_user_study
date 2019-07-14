public boolean isWriting(long broadcastId){
  return findWriter(broadcastId) != null;
}
