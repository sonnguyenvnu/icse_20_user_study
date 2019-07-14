@Override void captureVideo(File videoFile,int maxDuration,VideoCapturedCallback callback){
synchronized (mCameraLock) {
    try {
      if (prepareMediaRecorder(videoFile,maxDuration)) {
        mMediaRecorder.start();
        mRecording=true;
        this.mVideoCallback=callback;
      }
 else {
        releaseMediaRecorder();
      }
    }
 catch (    IOException e) {
      releaseMediaRecorder();
    }
catch (    RuntimeException e) {
      releaseMediaRecorder();
    }
  }
}
