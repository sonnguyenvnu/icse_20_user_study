public void openRound(final CameraSession session,final SurfaceTexture texture,final Runnable callback,final Runnable configureCallback){
  if (session == null || texture == null) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("failed to open round " + session + " tex = " + texture);
    }
    return;
  }
  threadPool.execute(() -> {
    Camera camera=session.cameraInfo.camera;
    try {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("start creating round camera session");
      }
      if (camera == null) {
        camera=session.cameraInfo.camera=Camera.open(session.cameraInfo.cameraId);
      }
      Camera.Parameters params=camera.getParameters();
      session.configureRoundCamera();
      if (configureCallback != null) {
        configureCallback.run();
      }
      camera.setPreviewTexture(texture);
      camera.startPreview();
      if (callback != null) {
        AndroidUtilities.runOnUIThread(callback);
      }
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("round camera session created");
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
