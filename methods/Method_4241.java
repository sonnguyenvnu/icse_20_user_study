@Override protected void onQueueInputBuffer(DecoderInputBuffer buffer){
  if (allowFirstBufferPositionDiscontinuity && !buffer.isDecodeOnly()) {
    if (Math.abs(buffer.timeUs - currentPositionUs) > 500000) {
      currentPositionUs=buffer.timeUs;
    }
    allowFirstBufferPositionDiscontinuity=false;
  }
  lastInputTimeUs=Math.max(buffer.timeUs,lastInputTimeUs);
}
