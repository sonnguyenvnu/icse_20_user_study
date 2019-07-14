public void updateButtonState(boolean ifSame,boolean animated){
  String fileName=null;
  File cacheFile=null;
  if (documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC || documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO) {
    if (documentAttach != null) {
      fileName=FileLoader.getAttachFileName(documentAttach);
      cacheFile=FileLoader.getPathToAttach(documentAttach);
    }
 else     if (inlineResult.content instanceof TLRPC.TL_webDocument) {
      fileName=Utilities.MD5(inlineResult.content.url) + "." + ImageLoader.getHttpUrlExtension(inlineResult.content.url,documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC ? "mp3" : "ogg");
      cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName);
    }
  }
 else   if (mediaWebpage) {
    if (inlineResult != null) {
      if (inlineResult.document instanceof TLRPC.TL_document) {
        fileName=FileLoader.getAttachFileName(inlineResult.document);
        cacheFile=FileLoader.getPathToAttach(inlineResult.document);
      }
 else       if (inlineResult.photo instanceof TLRPC.TL_photo) {
        currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(inlineResult.photo.sizes,AndroidUtilities.getPhotoSize(),true);
        fileName=FileLoader.getAttachFileName(currentPhotoObject);
        cacheFile=FileLoader.getPathToAttach(currentPhotoObject);
      }
 else       if (inlineResult.content instanceof TLRPC.TL_webDocument) {
        fileName=Utilities.MD5(inlineResult.content.url) + "." + ImageLoader.getHttpUrlExtension(inlineResult.content.url,"jpg");
        cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName);
      }
 else       if (inlineResult.thumb instanceof TLRPC.TL_webDocument) {
        fileName=Utilities.MD5(inlineResult.thumb.url) + "." + ImageLoader.getHttpUrlExtension(inlineResult.thumb.url,"jpg");
        cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName);
      }
    }
 else     if (documentAttach != null) {
      fileName=FileLoader.getAttachFileName(documentAttach);
      cacheFile=FileLoader.getPathToAttach(documentAttach);
    }
  }
  if (TextUtils.isEmpty(fileName)) {
    return;
  }
  if (!cacheFile.exists()) {
    DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,this);
    if (documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC || documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO) {
      boolean isLoading;
      if (documentAttach != null) {
        isLoading=FileLoader.getInstance(currentAccount).isLoadingFile(fileName);
      }
 else {
        isLoading=ImageLoader.getInstance().isLoadingHttpFile(fileName);
      }
      if (!isLoading) {
        buttonState=2;
        radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
      }
 else {
        buttonState=4;
        Float progress=ImageLoader.getInstance().getFileProgress(fileName);
        if (progress != null) {
          radialProgress.setProgress(progress,animated);
        }
 else {
          radialProgress.setProgress(0,animated);
        }
        radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
      }
    }
 else {
      buttonState=1;
      Float progress=ImageLoader.getInstance().getFileProgress(fileName);
      float setProgress=progress != null ? progress : 0;
      radialProgress.setProgress(setProgress,false);
      radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
    }
    invalidate();
  }
 else {
    DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
    if (documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC || documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO) {
      boolean playing=MediaController.getInstance().isPlayingMessage(currentMessageObject);
      if (!playing || playing && MediaController.getInstance().isMessagePaused()) {
        buttonState=0;
      }
 else {
        buttonState=1;
      }
      radialProgress.setProgress(1,animated);
    }
 else {
      buttonState=-1;
    }
    radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
    invalidate();
  }
}
