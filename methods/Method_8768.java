public void checkFileExist(){
  if (parentMessageObject != null && parentMessageObject.messageOwner.media != null) {
    String fileName=null;
    File cacheFile;
    if (TextUtils.isEmpty(parentMessageObject.messageOwner.attachPath) || !(new File(parentMessageObject.messageOwner.attachPath).exists())) {
      cacheFile=FileLoader.getPathToMessage(parentMessageObject.messageOwner);
      if (!cacheFile.exists()) {
        fileName=FileLoader.getAttachFileName(parentMessageObject.getDocument());
      }
    }
    loaded=false;
    if (fileName == null) {
      progressVisible=false;
      loading=false;
      loaded=true;
      DownloadController.getInstance(parentMessageObject.currentAccount).removeLoadingFileObserver(this);
    }
 else {
      DownloadController.getInstance(parentMessageObject.currentAccount).addLoadingFileObserver(fileName,this);
      loading=FileLoader.getInstance(parentMessageObject.currentAccount).isLoadingFile(fileName);
      if (loading) {
        progressVisible=true;
        Float progress=ImageLoader.getInstance().getFileProgress(fileName);
        if (progress == null) {
          progress=0.0f;
        }
        setProgress(progress,false);
      }
 else {
        progressVisible=false;
      }
    }
  }
 else {
    loading=false;
    loaded=true;
    progressVisible=false;
    setProgress(0,false);
    DownloadController.getInstance(parentMessageObject.currentAccount).removeLoadingFileObserver(this);
  }
  parentView.invalidate();
}
