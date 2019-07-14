private void createCamera(final SurfaceTexture surfaceTexture){
  AndroidUtilities.runOnUIThread(() -> {
    if (cameraThread == null) {
      return;
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("create camera session");
    }
    surfaceTexture.setDefaultBufferSize(previewSize.getWidth(),previewSize.getHeight());
    cameraSession=new CameraSession(selectedCamera,previewSize,pictureSize,ImageFormat.JPEG);
    cameraThread.setCurrentSession(cameraSession);
    CameraController.getInstance().openRound(cameraSession,surfaceTexture,() -> {
      if (cameraSession != null) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("camera initied");
        }
        cameraSession.setInitied();
      }
    }
,() -> cameraThread.setCurrentSession(cameraSession));
  }
);
}
