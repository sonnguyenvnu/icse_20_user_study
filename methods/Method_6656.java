public boolean isRecordingOrListeningByProximity(){
  return proximityTouched && (isRecordingAudio() || playingMessageObject != null && (playingMessageObject.isVoice() || playingMessageObject.isRoundVideo()));
}
