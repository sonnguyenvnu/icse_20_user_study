@Override protected void onDisabled(){
  inputFormat=null;
  audioTrackNeedsConfigure=true;
  waitingForKeys=false;
  try {
    setSourceDrmSession(null);
    releaseDecoder();
    audioSink.reset();
  }
  finally {
    eventDispatcher.disabled(decoderCounters);
  }
}
