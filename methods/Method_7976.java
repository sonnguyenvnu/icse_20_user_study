private void didPressMiniButton(boolean animated){
  if (miniButtonState == 0) {
    miniButtonState=1;
    radialProgress.setProgress(0,false);
    if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
      FileLoader.getInstance(currentAccount).loadFile(documentAttach,currentMessageObject,1,0);
    }
 else     if (documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO) {
      FileLoader.getInstance(currentAccount).loadFile(documentAttach,currentMessageObject,1,currentMessageObject.shouldEncryptPhotoOrVideo() ? 2 : 0);
    }
    radialProgress.setMiniIcon(getMiniIconForCurrentState(),false,true);
    invalidate();
  }
 else   if (miniButtonState == 1) {
    if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
      if (MediaController.getInstance().isPlayingMessage(currentMessageObject)) {
        MediaController.getInstance().cleanupPlayer(true,true);
      }
    }
    miniButtonState=0;
    FileLoader.getInstance(currentAccount).cancelLoadFile(documentAttach);
    radialProgress.setMiniIcon(getMiniIconForCurrentState(),false,true);
    invalidate();
  }
}
