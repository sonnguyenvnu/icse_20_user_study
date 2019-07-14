@Override void captureImage(final ImageCapturedCallback callback){
switch (mMethod) {
case METHOD_STANDARD:
synchronized (mCameraLock) {
      if (!capturingImage && mCamera != null) {
        capturingImage=true;
        int captureRotation=calculateCaptureRotation();
        mCameraParameters.setRotation(captureRotation);
        mCamera.setParameters(mCameraParameters);
        mCamera.takePicture(null,null,null,new Camera.PictureCallback(){
          @Override public void onPictureTaken(          byte[] data,          Camera camera){
            callback.imageCaptured(data);
            capturingImage=false;
synchronized (mCameraLock) {
              if (isCameraOpened()) {
                try {
                  stop();
                  start();
                }
 catch (                Exception e) {
                  notifyErrorListener(e);
                }
              }
            }
          }
        }
);
      }
 else {
        Log.w(TAG,"Unable, waiting for picture to be taken");
      }
      break;
    }
case METHOD_STILL:
synchronized (mCameraLock) {
    mCamera.setOneShotPreviewCallback(new Camera.PreviewCallback(){
      @Override public void onPreviewFrame(      byte[] data,      Camera camera){
        Camera.Parameters parameters=camera.getParameters();
        int width=parameters.getPreviewSize().width;
        int height=parameters.getPreviewSize().height;
        int rotation=calculateCaptureRotation();
        YuvOperator yuvOperator=new YuvOperator(data,width,height);
        yuvOperator.rotate(rotation);
        data=yuvOperator.getYuvData();
        int yuvOutputWidth=width;
        int yuvOutputHeight=height;
        if (rotation == 90 || rotation == 270) {
          yuvOutputWidth=height;
          yuvOutputHeight=width;
        }
        YuvImage yuvImage=new YuvImage(data,parameters.getPreviewFormat(),yuvOutputWidth,yuvOutputHeight,null);
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0,0,yuvImage.getWidth(),yuvImage.getHeight()),100,out);
        callback.imageCaptured(out.toByteArray());
      }
    }
);
    break;
  }
}
}
