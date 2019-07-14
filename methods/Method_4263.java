@Override public void render(long positionUs,long elapsedRealtimeUs) throws ExoPlaybackException {
  if (outputStreamEnded) {
    try {
      audioSink.playToEndOfStream();
    }
 catch (    AudioSink.WriteException e) {
      throw ExoPlaybackException.createForRenderer(e,getIndex());
    }
    return;
  }
  if (inputFormat == null) {
    flagsOnlyBuffer.clear();
    int result=readSource(formatHolder,flagsOnlyBuffer,true);
    if (result == C.RESULT_FORMAT_READ) {
      onInputFormatChanged(formatHolder.format);
    }
 else     if (result == C.RESULT_BUFFER_READ) {
      Assertions.checkState(flagsOnlyBuffer.isEndOfStream());
      inputStreamEnded=true;
      processEndOfStream();
      return;
    }
 else {
      return;
    }
  }
  maybeInitDecoder();
  if (decoder != null) {
    try {
      TraceUtil.beginSection("drainAndFeed");
      while (drainOutputBuffer()) {
      }
      while (feedInputBuffer()) {
      }
      TraceUtil.endSection();
    }
 catch (    AudioDecoderException|AudioSink.ConfigurationException|AudioSink.InitializationException|AudioSink.WriteException e) {
      throw ExoPlaybackException.createForRenderer(e,getIndex());
    }
    decoderCounters.ensureUpdated();
  }
}
