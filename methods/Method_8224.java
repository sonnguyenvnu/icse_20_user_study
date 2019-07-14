public boolean playFirstUnreadVoiceMessage(){
  if (chatActivityEnterView != null && chatActivityEnterView.isRecordingAudioVideo()) {
    return true;
  }
  for (int a=messages.size() - 1; a >= 0; a--) {
    MessageObject messageObject=messages.get(a);
    if ((messageObject.isVoice() || messageObject.isRoundVideo()) && messageObject.isContentUnread() && !messageObject.isOut()) {
      MediaController.getInstance().setVoiceMessagesPlaylist(MediaController.getInstance().playMessage(messageObject) ? createVoiceMessagesPlaylist(messageObject,true) : null,true);
      return true;
    }
  }
  if (Build.VERSION.SDK_INT >= 23 && getParentActivity() != null) {
    if (getParentActivity().checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
      getParentActivity().requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},3);
      return true;
    }
  }
  return false;
}
