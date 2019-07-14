@Override void stopVideo(){
synchronized (mCameraLock) {
    if (mRecording) {
      try {
        mMediaRecorder.stop();
        if (this.mVideoCallback != null) {
          mVideoCallback.videoCaptured(mMediaRecorderOutputFile);
          mVideoCallback=null;
        }
      }
 catch (      RuntimeException e) {
        mMediaRecorderOutputFile.delete();
      }
      releaseMediaRecorder();
      mRecording=false;
    }
    stop();
    start();
  }
}
