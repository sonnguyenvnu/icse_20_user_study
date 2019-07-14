private void flushDecoder() throws ExoPlaybackException {
  waitingForKeys=false;
  if (decoderReinitializationState != REINITIALIZATION_STATE_NONE) {
    releaseDecoder();
    maybeInitDecoder();
  }
 else {
    inputBuffer=null;
    if (outputBuffer != null) {
      outputBuffer.release();
      outputBuffer=null;
    }
    decoder.flush();
    decoderReceivedBuffers=false;
  }
}
