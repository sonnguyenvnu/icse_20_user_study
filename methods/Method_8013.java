@Override public void onProgressDownload(String fileName,float progress){
  radialProgress.setProgress(progress,true);
  if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
    if (buttonState != 4) {
      updateButtonState(false,true);
    }
  }
 else {
    if (buttonState != 1) {
      updateButtonState(false,true);
    }
  }
}
