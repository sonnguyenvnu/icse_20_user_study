public void didPressedButton(){
  if (buttonState == 0) {
    if (miniButtonState == 0) {
      FileLoader.getInstance(currentAccount).loadFile(currentMessageObject.getDocument(),currentMessageObject,1,0);
    }
    if (MediaController.getInstance().findMessageInPlaylistAndPlay(currentMessageObject)) {
      if (hasMiniProgress == 2 && miniButtonState != 1) {
        miniButtonState=1;
        radialProgress.setProgress(0,false);
        radialProgress.setMiniIcon(getMiniIconForCurrentState(),false,true);
      }
      buttonState=1;
      radialProgress.setIcon(getIconForCurrentState(),false,true);
      invalidate();
    }
  }
 else   if (buttonState == 1) {
    boolean result=MediaController.getInstance().pauseMessage(currentMessageObject);
    if (result) {
      buttonState=0;
      radialProgress.setIcon(getIconForCurrentState(),false,true);
      invalidate();
    }
  }
 else   if (buttonState == 2) {
    radialProgress.setProgress(0,false);
    FileLoader.getInstance(currentAccount).loadFile(currentMessageObject.getDocument(),currentMessageObject,1,0);
    buttonState=4;
    radialProgress.setIcon(getIconForCurrentState(),false,true);
    invalidate();
  }
 else   if (buttonState == 4) {
    FileLoader.getInstance(currentAccount).cancelLoadFile(currentMessageObject.getDocument());
    buttonState=2;
    radialProgress.setIcon(getIconForCurrentState(),false,true);
    invalidate();
  }
}
