public void stopPreview(final CameraSession session){
  if (session == null) {
    return;
  }
  threadPool.execute(() -> {
    Camera camera=session.cameraInfo.camera;
    try {
      if (camera == null) {
        camera=session.cameraInfo.camera=Camera.open(session.cameraInfo.cameraId);
      }
      camera.stopPreview();
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
