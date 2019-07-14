private boolean feedInputBuffer() throws AudioDecoderException, ExoPlaybackException {
  if (decoder == null || decoderReinitializationState == REINITIALIZATION_STATE_WAIT_END_OF_STREAM || inputStreamEnded) {
    return false;
  }
  if (inputBuffer == null) {
    inputBuffer=decoder.dequeueInputBuffer();
    if (inputBuffer == null) {
      return false;
    }
  }
  if (decoderReinitializationState == REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM) {
    inputBuffer.setFlags(C.BUFFER_FLAG_END_OF_STREAM);
    decoder.queueInputBuffer(inputBuffer);
    inputBuffer=null;
    decoderReinitializationState=REINITIALIZATION_STATE_WAIT_END_OF_STREAM;
    return false;
  }
  int result;
  if (waitingForKeys) {
    result=C.RESULT_BUFFER_READ;
  }
 else {
    result=readSource(formatHolder,inputBuffer,false);
  }
  if (result == C.RESULT_NOTHING_READ) {
    return false;
  }
  if (result == C.RESULT_FORMAT_READ) {
    onInputFormatChanged(formatHolder.format);
    return true;
  }
  if (inputBuffer.isEndOfStream()) {
    inputStreamEnded=true;
    decoder.queueInputBuffer(inputBuffer);
    inputBuffer=null;
    return false;
  }
  boolean bufferEncrypted=inputBuffer.isEncrypted();
  waitingForKeys=shouldWaitForKeys(bufferEncrypted);
  if (waitingForKeys) {
    return false;
  }
  inputBuffer.flip();
  onQueueInputBuffer(inputBuffer);
  decoder.queueInputBuffer(inputBuffer);
  decoderReceivedBuffers=true;
  decoderCounters.inputBufferCount++;
  inputBuffer=null;
  return true;
}
