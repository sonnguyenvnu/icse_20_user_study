public boolean checkRecordLocked(){
  if (chatActivityEnterView != null && chatActivityEnterView.isRecordLocked()) {
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    if (chatActivityEnterView.isInVideoMode()) {
      builder.setTitle(LocaleController.getString("DiscardVideoMessageTitle",R.string.DiscardVideoMessageTitle));
      builder.setMessage(LocaleController.getString("DiscardVideoMessageDescription",R.string.DiscardVideoMessageDescription));
    }
 else {
      builder.setTitle(LocaleController.getString("DiscardVoiceMessageTitle",R.string.DiscardVoiceMessageTitle));
      builder.setMessage(LocaleController.getString("DiscardVoiceMessageDescription",R.string.DiscardVoiceMessageDescription));
    }
    builder.setPositiveButton(LocaleController.getString("DiscardVoiceMessageAction",R.string.DiscardVoiceMessageAction),(dialog,which) -> {
      if (chatActivityEnterView != null) {
        chatActivityEnterView.cancelRecordingAudioVideo();
      }
    }
);
    builder.setNegativeButton(LocaleController.getString("Continue",R.string.Continue),null);
    showDialog(builder.create());
    return true;
  }
  return false;
}
