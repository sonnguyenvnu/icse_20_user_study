private void updateCollectStatus(){
  if (mCollected) {
    return;
  }
  CollectItemManager manager=CollectItemManager.getInstance();
  boolean sending=manager.isWriting(mItem);
  Activity activity=getActivity();
  activity.setTitle(sending ? getString(R.string.item_collection_title_saving_format,mItem.getType().getName(activity)) : mItem.title);
  boolean enabled=!sending;
  if (mCollectMenuItem != null) {
    mCollectMenuItem.setEnabled(enabled);
  }
  mStateLayout.setEnabled(enabled);
  mStateSpinner.setEnabled(enabled);
  mRatingBar.setIsIndicator(!enabled);
  mTagsEdit.setEnabled(enabled);
  mCommentEdit.setEnabled(enabled);
  mShareToBroadcastCheckBox.setEnabled(enabled);
  mShareToWeiboCheckBox.setEnabled(enabled);
  if (sending) {
    mStateSpinner.setSelection(mStateAdapter.getPositionForState(manager.getState(mItem)),false);
    mRatingBar.setRating(manager.getRating(mItem));
    setTags(manager.getTags(mItem));
    mCommentEdit.setText(manager.getComment(mItem));
  }
}
