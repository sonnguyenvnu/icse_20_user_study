private void updateRebroadcastStatus(){
  if (mRebroadcasted) {
    return;
  }
  RebroadcastBroadcastManager manager=RebroadcastBroadcastManager.getInstance();
  boolean hasBroadcast=mBroadcastResource.hasEffectiveBroadcast();
  boolean rebroadcasting=hasBroadcast && manager.isWriting(mBroadcastResource.getEffectiveBroadcastId());
  getActivity().setTitle(rebroadcasting ? R.string.broadcast_rebroadcast_title_rebroadcasting : R.string.broadcast_rebroadcast_title);
  boolean enabled=!rebroadcasting;
  mTextEdit.setEnabled(enabled);
  if (mRebroadcastMenuItem != null) {
    mRebroadcastMenuItem.setEnabled(enabled);
  }
  if (rebroadcasting) {
    mTextEdit.setText(manager.getText(mBroadcastResource.getEffectiveBroadcastId()));
  }
}
