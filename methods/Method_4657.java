/** 
 * Adjusts a seek point offset to take into account the track with the given  {@code sampleTable}, for a given  {@code seekTimeUs}.
 * @param sampleTable The sample table to use.
 * @param seekTimeUs The seek time in microseconds.
 * @param offset The current offset.
 * @return The adjusted offset.
 */
private static long maybeAdjustSeekOffset(TrackSampleTable sampleTable,long seekTimeUs,long offset){
  int sampleIndex=getSynchronizationSampleIndex(sampleTable,seekTimeUs);
  if (sampleIndex == C.INDEX_UNSET) {
    return offset;
  }
  long sampleOffset=sampleTable.offsets[sampleIndex];
  return Math.min(sampleOffset,offset);
}
