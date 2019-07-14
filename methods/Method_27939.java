@Override public boolean isPreviouslyReacted(long commentId,int vId){
  return getReactionsProvider().isPreviouslyReacted(commentId,vId);
}
