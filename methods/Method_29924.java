@Override public void onDeleteComment(Comment comment){
  ConfirmDeleteCommentDialogFragment.show(comment.id,this);
}
