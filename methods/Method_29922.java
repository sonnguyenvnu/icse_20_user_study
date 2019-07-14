private void onShowCommentAction(Comment comment){
  boolean canReplyTo=canSendComment();
  boolean canDelete=(mBroadcastAdapter.hasBroadcast() && mBroadcastAdapter.getBroadcast().isAuthorOneself()) || comment.isAuthorOneself();
  CommentActionDialogFragment.show(comment,canReplyTo,canDelete,this);
}
