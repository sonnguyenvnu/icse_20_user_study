public void bindForRebroadcast(Broadcast broadcast){
  ViewUtils.setVisibleOrGone(mAuthorTimeActionLayout,false);
  if (broadcast.isSimpleRebroadcast()) {
    mTextText.setText(broadcast.parentBroadcast != null ? broadcast.parentBroadcast.getTextWithEntitiesAsParent(mTextText.getContext()) : null);
  }
 else {
    mTextText.setText(broadcast.rebroadcastedBroadcast != null ? broadcast.getTextWithEntitiesAsParent(mTextText.getContext()) : null);
  }
  bindRebroadcastedAttachmentImages(null,broadcast.rebroadcastedBroadcast != null ? broadcast.rebroadcastedBroadcast : broadcast);
  ViewUtils.setVisibleOrGone(mRebroadcastedAttachmentImagesSpace,false);
  ViewUtils.setVisibleOrGone(mActionsLayout,false);
}
