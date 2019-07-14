public void close(final CameraSession session,final CountDownLatch countDownLatch,final Runnable beforeDestroyRunnable){
  session.destroy();
  threadPool.execute(() -> {
    if (beforeDestroyRunnable != null) {
      beforeDestroyRunnable.run();
    }
    if (session.cameraInfo.camera == null) {
      return;
    }
    try {
      session.cameraInfo.camera.stopPreview();
      session.cameraInfo.camera.setPreviewCallbackWithBuffer(null);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    try {
      session.cameraInfo.camera.release();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    session.cameraInfo.camera=null;
    if (countDownLatch != null) {
      countDownLatch.countDown();
    }
  }
);
  if (countDownLatch != null) {
    try {
      countDownLatch.await();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
