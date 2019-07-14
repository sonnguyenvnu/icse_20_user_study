private void requestVideoPreview(int request){
  if (videoPreviewMessageObject != null) {
    MediaController.getInstance().cancelVideoConvert(videoPreviewMessageObject);
  }
  boolean wasRequestingPreview=requestingPreview && !tryStartRequestPreviewOnFinish;
  requestingPreview=false;
  loadInitialVideo=false;
  progressView.setVisibility(View.INVISIBLE);
  if (request == 1) {
    if (selectedCompression == compressionsCount - 1) {
      tryStartRequestPreviewOnFinish=false;
      if (!wasRequestingPreview) {
        preparePlayer(currentPlayingVideoFile,false,false);
      }
 else {
        progressView.setVisibility(View.VISIBLE);
        loadInitialVideo=true;
      }
    }
 else {
      requestingPreview=true;
      releasePlayer(false);
      if (videoPreviewMessageObject == null) {
        TLRPC.TL_message message=new TLRPC.TL_message();
        message.id=0;
        message.message="";
        message.media=new TLRPC.TL_messageMediaEmpty();
        message.action=new TLRPC.TL_messageActionEmpty();
        videoPreviewMessageObject=new MessageObject(UserConfig.selectedAccount,message,false);
        videoPreviewMessageObject.messageOwner.attachPath=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),"video_preview.mp4").getAbsolutePath();
        videoPreviewMessageObject.videoEditedInfo=new VideoEditedInfo();
        videoPreviewMessageObject.videoEditedInfo.rotationValue=rotationValue;
        videoPreviewMessageObject.videoEditedInfo.originalWidth=originalWidth;
        videoPreviewMessageObject.videoEditedInfo.originalHeight=originalHeight;
        videoPreviewMessageObject.videoEditedInfo.framerate=videoFramerate;
        videoPreviewMessageObject.videoEditedInfo.originalPath=currentPlayingVideoFile.getPath();
      }
      long start=videoPreviewMessageObject.videoEditedInfo.startTime=startTime;
      long end=videoPreviewMessageObject.videoEditedInfo.endTime=endTime;
      if (start == -1) {
        start=0;
      }
      if (end == -1) {
        end=(long)(videoDuration * 1000);
      }
      if (end - start > 5000000) {
        videoPreviewMessageObject.videoEditedInfo.endTime=start + 5000000;
      }
      videoPreviewMessageObject.videoEditedInfo.bitrate=bitrate;
      videoPreviewMessageObject.videoEditedInfo.resultWidth=resultWidth;
      videoPreviewMessageObject.videoEditedInfo.resultHeight=resultHeight;
      if (!MediaController.getInstance().scheduleVideoConvert(videoPreviewMessageObject,true)) {
        tryStartRequestPreviewOnFinish=true;
      }
      requestingPreview=true;
      progressView.setVisibility(View.VISIBLE);
    }
  }
 else {
    tryStartRequestPreviewOnFinish=false;
    if (request == 2) {
      preparePlayer(currentPlayingVideoFile,false,false);
    }
  }
  containerView.invalidate();
}
