public void open(final CameraSession session,final SurfaceTexture texture,final Runnable callback,final Runnable prestartCallback){
  if (session == null || texture == null) {
    return;
  }
  threadPool.execute(() -> {
    Camera camera=session.cameraInfo.camera;
    try {
      if (camera == null) {
        camera=session.cameraInfo.camera=Camera.open(session.cameraInfo.cameraId);
      }
      Camera.Parameters params=camera.getParameters();
      List<String> rawFlashModes=params.getSupportedFlashModes();
      availableFlashModes.clear();
      if (rawFlashModes != null) {
        for (int a=0; a < rawFlashModes.size(); a++) {
          String rawFlashMode=rawFlashModes.get(a);
          if (rawFlashMode.equals(Camera.Parameters.FLASH_MODE_OFF) || rawFlashMode.equals(Camera.Parameters.FLASH_MODE_ON) || rawFlashMode.equals(Camera.Parameters.FLASH_MODE_AUTO)) {
            availableFlashModes.add(rawFlashMode);
          }
        }
        session.checkFlashMode(availableFlashModes.get(0));
      }
      if (prestartCallback != null) {
        prestartCallback.run();
      }
      session.configurePhotoCamera();
      camera.setPreviewTexture(texture);
      camera.startPreview();
      if (callback != null) {
        AndroidUtilities.runOnUIThread(callback);
      }
    }
 catch (    Exception e) {
      session.cameraInfo.camera=null;
      if (camera != null) {
        camera.release();
      }
      FileLog.e(e);
    }
  }
);
}
