private void startRecognizing(){
  backgroundHandlerThread.start();
  handler=new Handler(backgroundHandlerThread.getLooper());
  AndroidUtilities.runOnUIThread(new Runnable(){
    @Override public void run(){
      if (cameraView != null && !recognized && cameraView.getCameraSession() != null) {
        cameraView.getCameraSession().setOneShotPreviewCallback(MrzCameraActivity.this);
        AndroidUtilities.runOnUIThread(this,500);
      }
    }
  }
);
}
