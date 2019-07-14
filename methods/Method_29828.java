protected void appendAndNotifyListener(List<Comment> commentList){
  append(commentList);
  getListener().onCommentListAppended(getRequestCode(),commentList);
}
