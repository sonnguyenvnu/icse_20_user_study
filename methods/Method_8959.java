public void setMessageObject(MessageObject messageObject){
  if (currentMessageObject != messageObject) {
    currentAccount=messageObject.currentAccount;
    seekBar.setColors(Theme.getColor(Theme.key_chat_inAudioSeekbar),Theme.getColor(Theme.key_chat_inAudioSeekbar),Theme.getColor(Theme.key_chat_inAudioSeekbarFill),Theme.getColor(Theme.key_chat_inAudioSeekbarFill),Theme.getColor(Theme.key_chat_inAudioSeekbarSelected));
    progressView.setProgressColors(0xffd9e2eb,0xff86c5f8);
    currentMessageObject=messageObject;
    wasLayout=false;
    requestLayout();
  }
  updateButtonState();
}
