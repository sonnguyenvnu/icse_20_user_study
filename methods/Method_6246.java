public void stopVideoRecording(final CameraSession session,final boolean abandon){
  threadPool.execute(() -> {
    try {
      CameraInfo info=session.cameraInfo;
      final Camera camera=info.camera;
      if (camera != null && recorder != null) {
        MediaRecorder tempRecorder=recorder;
        recorder=null;
        try {
          tempRecorder.stop();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        try {
          tempRecorder.release();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        try {
          camera.reconnect();
          camera.startPreview();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        try {
          session.stopVideoRecording();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      try {
        Camera.Parameters params=camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(params);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      threadPool.execute(() -> {
        try {
          Camera.Parameters params=camera.getParameters();
          params.setFlashMode(session.getCurrentFlashMode());
          camera.setParameters(params);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
);
      if (!abandon && onVideoTakeCallback != null) {
        finishRecordingVideo();
      }
 else {
        onVideoTakeCallback=null;
      }
    }
 catch (    Exception ignore) {
    }
  }
);
}
