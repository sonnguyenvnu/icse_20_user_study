private boolean drainOutputBuffer() throws ExoPlaybackException, AudioDecoderException, AudioSink.ConfigurationException, AudioSink.InitializationException, AudioSink.WriteException {
  if (outputBuffer == null) {
    outputBuffer=decoder.dequeueOutputBuffer();
    if (outputBuffer == null) {
      return false;
    }
    if (outputBuffer.skippedOutputBufferCount > 0) {
      decoderCounters.skippedOutputBufferCount+=outputBuffer.skippedOutputBufferCount;
      audioSink.handleDiscontinuity();
    }
  }
  if (outputBuffer.isEndOfStream()) {
    if (decoderReinitializationState == REINITIALIZATION_STATE_WAIT_END_OF_STREAM) {
      releaseDecoder();
      maybeInitDecoder();
      audioTrackNeedsConfigure=true;
    }
 else {
      outputBuffer.release();
      outputBuffer=null;
      processEndOfStream();
    }
    return false;
  }
  if (audioTrackNeedsConfigure) {
    Format outputFormat=getOutputFormat();
    audioSink.configure(outputFormat.pcmEncoding,outputFormat.channelCount,outputFormat.sampleRate,0,null,encoderDelay,encoderPadding);
    audioTrackNeedsConfigure=false;
  }
  if (audioSink.handleBuffer(outputBuffer.data,outputBuffer.timeUs)) {
    decoderCounters.renderedOutputBufferCount++;
    outputBuffer.release();
    outputBuffer=null;
    return true;
  }
  return false;
}
