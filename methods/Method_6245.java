@Override public void onInfo(MediaRecorder mediaRecorder,int what,int extra){
  if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED || what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED || what == MediaRecorder.MEDIA_RECORDER_INFO_UNKNOWN) {
    MediaRecorder tempRecorder=recorder;
    recorder=null;
    if (tempRecorder != null) {
      tempRecorder.stop();
      tempRecorder.release();
    }
    if (onVideoTakeCallback != null) {
      finishRecordingVideo();
    }
  }
}
