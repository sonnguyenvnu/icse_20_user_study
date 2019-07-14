public void downloadAudioIfNeed(){
  if (buttonState == 2) {
    FileLoader.getInstance(currentAccount).loadFile(currentMessageObject.getDocument(),currentMessageObject,1,0);
    buttonState=3;
    invalidate();
  }
}
