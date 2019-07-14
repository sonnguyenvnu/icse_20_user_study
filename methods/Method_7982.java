@Override public void onProgressUpload(String fileName,float progress,boolean isEncrypted){
  radialProgress.setProgress(progress,true);
  if (progress == 1.0f && currentPosition != null) {
    boolean sending=SendMessagesHelper.getInstance(currentAccount).isSendingMessage(currentMessageObject.getId());
    if (sending && buttonState == 1) {
      drawRadialCheckBackground=true;
      getIconForCurrentState();
      radialProgress.setIcon(MediaActionDrawable.ICON_CHECK,false,true);
    }
  }
}
