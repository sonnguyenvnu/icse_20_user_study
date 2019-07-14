public void recordVideo(final CameraSession session,final File path,final VideoTakeCallback callback,final Runnable onVideoStartRecord){
  if (session == null) {
    return;
  }
  final CameraInfo info=session.cameraInfo;
  final Camera camera=info.camera;
  threadPool.execute(() -> {
    try {
      if (camera != null) {
        try {
          Camera.Parameters params=camera.getParameters();
          params.setFlashMode(session.getCurrentFlashMode().equals(Camera.Parameters.FLASH_MODE_ON) ? Camera.Parameters.FLASH_MODE_TORCH : Camera.Parameters.FLASH_MODE_OFF);
          camera.setParameters(params);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        camera.unlock();
        try {
          recorder=new MediaRecorder();
          recorder.setCamera(camera);
          recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
          recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
          session.configureRecorder(1,recorder);
          recorder.setOutputFile(path.getAbsolutePath());
          recorder.setMaxFileSize(1024 * 1024 * 1024);
          recorder.setVideoFrameRate(30);
          recorder.setMaxDuration(0);
          Size pictureSize;
          pictureSize=new Size(16,9);
          pictureSize=CameraController.chooseOptimalSize(info.getPictureSizes(),720,480,pictureSize);
          recorder.setVideoEncodingBitRate(900000 * 2);
          recorder.setVideoSize(pictureSize.getWidth(),pictureSize.getHeight());
          recorder.setOnInfoListener(CameraController.this);
          recorder.prepare();
          recorder.start();
          onVideoTakeCallback=callback;
          recordedFile=path.getAbsolutePath();
          if (onVideoStartRecord != null) {
            AndroidUtilities.runOnUIThread(onVideoStartRecord);
          }
        }
 catch (        Exception e) {
          recorder.release();
          recorder=null;
          FileLog.e(e);
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
