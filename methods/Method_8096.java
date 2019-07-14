private void didPressedMiniButton(boolean animated){
  if (miniButtonState == 0) {
    miniButtonState=1;
    radialProgress.setProgress(0,false);
    FileLoader.getInstance(currentAccount).loadFile(currentMessageObject.getDocument(),currentMessageObject,1,0);
    radialProgress.setMiniIcon(getMiniIconForCurrentState(),false,true);
    invalidate();
  }
 else   if (miniButtonState == 1) {
    if (MediaController.getInstance().isPlayingMessage(currentMessageObject)) {
      MediaController.getInstance().cleanupPlayer(true,true);
    }
    miniButtonState=0;
    FileLoader.getInstance(currentAccount).cancelLoadFile(currentMessageObject.getDocument());
    radialProgress.setMiniIcon(getMiniIconForCurrentState(),false,true);
    invalidate();
  }
}
