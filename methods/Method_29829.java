@Subscribe(threadMode=ThreadMode.POSTING) public void onCommentDeleted(CommentDeletedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<Comment> commentList=get();
  for (int i=0, size=commentList.size(); i < size; ) {
    Comment comment=commentList.get(i);
    if (comment.id == event.commentId) {
      commentList.remove(i);
      getListener().onCommentRemoved(getRequestCode(),i);
      --size;
    }
 else {
      ++i;
    }
  }
}
