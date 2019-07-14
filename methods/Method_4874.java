@Override public void render(long positionUs,long elapsedRealtimeUs) throws ExoPlaybackException {
  if (outputStreamEnded) {
    renderToEndOfStream();
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
  maybeInitCodec();
  if (codec != null) {
    long drainStartTimeMs=SystemClock.elapsedRealtime();
    TraceUtil.beginSection("drainAndFeed");
    while (drainOutputBuffer(positionUs,elapsedRealtimeUs)) {
    }
    while (feedInputBuffer() && shouldContinueFeeding(drainStartTimeMs)) {
    }
    TraceUtil.endSection();
  }
 else {
    decoderCounters.skippedInputBufferCount+=skipSource(positionUs);
    flagsOnlyBuffer.clear();
    int result=readSource(formatHolder,flagsOnlyBuffer,false);
    if (result == C.RESULT_FORMAT_READ) {
      onInputFormatChanged(formatHolder.format);
    }
 else     if (result == C.RESULT_BUFFER_READ) {
      Assertions.checkState(flagsOnlyBuffer.isEndOfStream());
      inputStreamEnded=true;
      processEndOfStream();
    }
  }
  decoderCounters.ensureUpdated();
}
