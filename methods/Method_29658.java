private void openCamera(){
synchronized (mCameraLock) {
    if (mCamera != null) {
      releaseCamera();
    }
    mCamera=Camera.open(mCameraId);
    mCameraParameters=mCamera.getParameters();
    collectCameraProperties();
    adjustCameraParameters();
    if (Build.VERSION.SDK_INT >= 16) {
      mCamera.setAutoFocusMoveCallback(new Camera.AutoFocusMoveCallback(){
        @Override public void onAutoFocusMoving(        boolean b,        Camera camera){
          CameraKitEvent event=new CameraKitEvent(CameraKitEvent.TYPE_FOCUS_MOVED);
          event.getData().putBoolean("started",b);
          mEventDispatcher.dispatch(event);
        }
      }
);
    }
    mEventDispatcher.dispatch(new CameraKitEvent(CameraKitEvent.TYPE_CAMERA_OPEN));
    if (mTextDetector != null) {
      mFrameProcessor=new FrameProcessingRunnable(mTextDetector,mPreviewSize,mCamera);
      mFrameProcessor.start();
    }
  }
}
