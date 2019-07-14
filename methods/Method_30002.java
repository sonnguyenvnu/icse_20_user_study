private void updateSendStatus(){
  if (mSent) {
    return;
  }
  SendBroadcastManager manager=SendBroadcastManager.getInstance();
  boolean sending=manager.isWriting(mWriterId);
  getActivity().setTitle(sending ? R.string.broadcast_send_title_sending : R.string.broadcast_send_title);
  boolean enabled=!sending;
  if (mSendMenuItem != null) {
    mSendMenuItem.setEnabled(enabled);
  }
  mTextEdit.setEnabled(enabled);
  if (sending) {
    mTextEdit.setText(manager.getText(mWriterId));
  }
  mAddImageButton.setEnabled(enabled);
  mAddMoreImageButton.setEnabled(enabled);
  mRemoveAllImagesButton.setEnabled(enabled);
  mAddLinkButton.setEnabled(enabled);
  mEditLinkButton.setEnabled(enabled);
  mRemoveLinkButton.setEnabled(enabled);
  mAddMentionButton.setEnabled(enabled);
  mAddTopicButton.setEnabled(enabled);
}
