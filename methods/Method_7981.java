@Override public void onProgressDownload(String fileName,float progress){
  if (drawVideoImageButton) {
    videoRadialProgress.setProgress(progress,true);
  }
 else {
    radialProgress.setProgress(progress,true);
  }
  if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
    if (hasMiniProgress != 0) {
      if (miniButtonState != 1) {
        updateButtonState(false,false,false);
      }
    }
 else {
      if (buttonState != 4) {
        updateButtonState(false,false,false);
      }
    }
  }
 else {
    if (hasMiniProgress != 0) {
      if (miniButtonState != 1) {
        updateButtonState(false,false,false);
      }
    }
 else {
      if (buttonState != 1) {
        updateButtonState(false,false,false);
      }
    }
  }
}
