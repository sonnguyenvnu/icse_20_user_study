public boolean isWriting(long broadcastId,long commentId){
  return findWriter(broadcastId,commentId) != null;
}
