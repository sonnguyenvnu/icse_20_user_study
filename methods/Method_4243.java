@Override protected boolean processOutputBuffer(long positionUs,long elapsedRealtimeUs,MediaCodec codec,ByteBuffer buffer,int bufferIndex,int bufferFlags,long bufferPresentationTimeUs,boolean shouldSkip,Format format) throws ExoPlaybackException {
  if (codecNeedsEosBufferTimestampWorkaround && bufferPresentationTimeUs == 0 && (bufferFlags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0 && lastInputTimeUs != C.TIME_UNSET) {
    bufferPresentationTimeUs=lastInputTimeUs;
  }
  if (passthroughEnabled && (bufferFlags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
    codec.releaseOutputBuffer(bufferIndex,false);
    return true;
  }
  if (shouldSkip) {
    codec.releaseOutputBuffer(bufferIndex,false);
    decoderCounters.skippedOutputBufferCount++;
    audioSink.handleDiscontinuity();
    return true;
  }
  try {
    if (audioSink.handleBuffer(buffer,bufferPresentationTimeUs)) {
      codec.releaseOutputBuffer(bufferIndex,false);
      decoderCounters.renderedOutputBufferCount++;
      return true;
    }
  }
 catch (  AudioSink.InitializationException|AudioSink.WriteException e) {
    throw ExoPlaybackException.createForRenderer(e,getIndex());
  }
  return false;
}
