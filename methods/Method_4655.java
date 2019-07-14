/** 
 * Updates every track's sample index to point its latest sync sample before/at  {@code timeUs}.
 */
private void updateSampleIndices(long timeUs){
  for (  Mp4Track track : tracks) {
    TrackSampleTable sampleTable=track.sampleTable;
    int sampleIndex=sampleTable.getIndexOfEarlierOrEqualSynchronizationSample(timeUs);
    if (sampleIndex == C.INDEX_UNSET) {
      sampleIndex=sampleTable.getIndexOfLaterOrEqualSynchronizationSample(timeUs);
    }
    track.sampleIndex=sampleIndex;
  }
}
