public void captureImage(final CameraKitEventCallback<CameraKitImage> callback){
  mCameraImpl.captureImage(new CameraImpl.ImageCapturedCallback(){
    @Override public void imageCaptured(    byte[] jpeg){
      PostProcessor postProcessor=new PostProcessor(jpeg);
      postProcessor.setJpegQuality(mJpegQuality);
      postProcessor.setFacing(mFacing);
      if (mCropOutput)       postProcessor.setCropOutput(AspectRatio.of(getWidth(),getHeight()));
      CameraKitImage image=new CameraKitImage(postProcessor.getJpeg());
      if (callback != null)       callback.callback(image);
      mEventDispatcher.dispatch(image);
    }
  }
);
}
