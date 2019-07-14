@Override public void seek(long position,long timeUs){
  Assertions.checkState(mode != MODE_HLS);
  int timestampAdjustersCount=timestampAdjusters.size();
  for (int i=0; i < timestampAdjustersCount; i++) {
    TimestampAdjuster timestampAdjuster=timestampAdjusters.get(i);
    boolean hasNotEncounteredFirstTimestamp=timestampAdjuster.getTimestampOffsetUs() == C.TIME_UNSET;
    if (hasNotEncounteredFirstTimestamp || (timestampAdjuster.getTimestampOffsetUs() != 0 && timestampAdjuster.getFirstSampleTimestampUs() != timeUs)) {
      timestampAdjuster.reset();
      timestampAdjuster.setFirstSampleTimestampUs(timeUs);
    }
  }
  if (timeUs != 0 && tsBinarySearchSeeker != null) {
    tsBinarySearchSeeker.setSeekTargetUs(timeUs);
  }
  tsPacketBuffer.reset();
  continuityCounters.clear();
  for (int i=0; i < tsPayloadReaders.size(); i++) {
    tsPayloadReaders.valueAt(i).seek();
  }
  bytesSinceLastSync=0;
}
