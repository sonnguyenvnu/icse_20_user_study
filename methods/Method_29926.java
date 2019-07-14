@Subscribe(threadMode=ThreadMode.POSTING) public void onBroadcastCommentSent(BroadcastCommentSentEvent event){
  if (event.isFromMyself(this)) {
    return;
  }
  if (mResource.isEffectiveBroadcastId(event.broadcastId)) {
    mBroadcastCommentList.scrollToPosition(mAdapter.getItemCount() - 1);
    mCommentEdit.setText(null);
    updateSendCommentStatus();
  }
}
