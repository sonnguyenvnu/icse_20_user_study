public void showCamera(){
  if (textureView != null) {
    return;
  }
  switchCameraButton.setImageResource(R.drawable.camera_revert1);
  textureOverlayView.setAlpha(1.0f);
  if (lastBitmap == null) {
    try {
      File file=new File(ApplicationLoader.getFilesDirFixed(),"icthumb.jpg");
      lastBitmap=BitmapFactory.decodeFile(file.getAbsolutePath());
    }
 catch (    Throwable ignore) {
    }
  }
  if (lastBitmap != null) {
    textureOverlayView.setImageBitmap(lastBitmap);
  }
 else {
    textureOverlayView.setImageResource(R.drawable.icplaceholder);
  }
  cameraReady=false;
  isFrontface=true;
  selectedCamera=null;
  recordedTime=0;
  progress=0;
  cancelled=false;
  file=null;
  encryptedFile=null;
  key=null;
  iv=null;
  if (!initCamera()) {
    return;
  }
  MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
  cameraFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),SharedConfig.getLastLocalId() + ".mp4");
  SharedConfig.saveConfig();
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("show round camera");
  }
  textureView=new TextureView(getContext());
  textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener(){
    @Override public void onSurfaceTextureAvailable(    SurfaceTexture surface,    int width,    int height){
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("camera surface available");
      }
      if (cameraThread == null && surface != null) {
        if (cancelled) {
          return;
        }
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("start create thread");
        }
        cameraThread=new CameraGLThread(surface,width,height);
      }
    }
    @Override public void onSurfaceTextureSizeChanged(    SurfaceTexture surface,    final int width,    final int height){
    }
    @Override public boolean onSurfaceTextureDestroyed(    SurfaceTexture surface){
      if (cameraThread != null) {
        cameraThread.shutdown(0);
        cameraThread=null;
      }
      if (cameraSession != null) {
        CameraController.getInstance().close(cameraSession,null,null);
      }
      return true;
    }
    @Override public void onSurfaceTextureUpdated(    SurfaceTexture surface){
    }
  }
);
  cameraContainer.addView(textureView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  setVisibility(VISIBLE);
  startAnimation(true);
}
