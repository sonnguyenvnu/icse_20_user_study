private void updateSendCommentStatus(){
  boolean canSendComment=canSendComment();
  SendBroadcastCommentManager manager=SendBroadcastCommentManager.getInstance();
  boolean hasBroadcast=mResource.hasEffectiveBroadcast();
  boolean sendingComment=hasBroadcast && manager.isWriting(mResource.getEffectiveBroadcastId());
  boolean enabled=canSendComment && !sendingComment;
  mCommentEdit.setEnabled(enabled);
  mSendButton.setEnabled(enabled);
  mCommentEdit.setHint(!hasBroadcast || canSendComment ? R.string.broadcast_send_comment_hint : R.string.broadcast_send_comment_hint_disabled);
  if (sendingComment) {
    mCommentEdit.setText(manager.getComment(mResource.getEffectiveBroadcastId()));
  }
}
