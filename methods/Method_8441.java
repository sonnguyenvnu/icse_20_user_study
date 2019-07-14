public void cancelRecordingAudioVideo(){
  if (hasRecordVideo && videoSendButton.getTag() != null) {
    CameraController.getInstance().cancelOnInitRunnable(onFinishInitCameraRunnable);
    delegate.needStartRecordVideo(2);
  }
 else {
    delegate.needStartRecordAudio(0);
    MediaController.getInstance().stopRecording(0);
  }
  recordingAudioVideo=false;
  updateRecordIntefrace();
}
