private void didPressedButton(){
  if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
    if (buttonState == 0) {
      if (MediaController.getInstance().playMessage(currentMessageObject)) {
        buttonState=1;
        radialProgress.setIcon(getIconForCurrentState(),false,true);
        invalidate();
      }
    }
 else     if (buttonState == 1) {
      boolean result=MediaController.getInstance().pauseMessage(currentMessageObject);
      if (result) {
        buttonState=0;
        radialProgress.setIcon(getIconForCurrentState(),false,true);
        invalidate();
      }
    }
 else     if (buttonState == 2) {
      radialProgress.setProgress(0,false);
      if (documentAttach != null) {
        FileLoader.getInstance(currentAccount).loadFile(documentAttach,inlineResult,1,0);
      }
 else       if (inlineResult.content instanceof TLRPC.TL_webDocument) {
        FileLoader.getInstance(currentAccount).loadFile(WebFile.createWithWebDocument(inlineResult.content),1,1);
      }
      buttonState=4;
      radialProgress.setIcon(getIconForCurrentState(),false,true);
      invalidate();
    }
 else     if (buttonState == 4) {
      if (documentAttach != null) {
        FileLoader.getInstance(currentAccount).cancelLoadFile(documentAttach);
      }
 else       if (inlineResult.content instanceof TLRPC.TL_webDocument) {
        FileLoader.getInstance(currentAccount).cancelLoadFile(WebFile.createWithWebDocument(inlineResult.content));
      }
      buttonState=2;
      radialProgress.setIcon(getIconForCurrentState(),false,true);
      invalidate();
    }
  }
}
