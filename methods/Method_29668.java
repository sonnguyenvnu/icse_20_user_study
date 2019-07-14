private void releaseMediaRecorder(){
synchronized (mCameraLock) {
    if (mMediaRecorder != null) {
      mMediaRecorder.reset();
      mMediaRecorder.release();
      mMediaRecorder=null;
      mCamera.lock();
    }
  }
}
