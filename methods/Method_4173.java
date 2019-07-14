private boolean willPauseWhenDucked(){
  return audioAttributes != null && audioAttributes.contentType == C.CONTENT_TYPE_SPEECH;
}
