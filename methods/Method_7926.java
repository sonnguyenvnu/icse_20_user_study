@Override public void onProgressDownload(String fileName,float progress){
  radialProgress.setProgress(progress,true);
  if (hasMiniProgress != 0) {
    if (miniButtonState != 1) {
      updateButtonState(false,true);
    }
  }
 else {
    if (buttonState != 4) {
      updateButtonState(false,true);
    }
  }
}
