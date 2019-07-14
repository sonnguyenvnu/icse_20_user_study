@Override public void onReplyToComment(Comment comment){
  mCommentEdit.getText().replace(mCommentEdit.getSelectionStart(),mCommentEdit.getSelectionEnd(),DoubanUtils.makeMentionString(comment.author));
  onShowCommentIme();
}
