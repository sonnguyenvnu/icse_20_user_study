/** 
 * Returns the index of the track that contains the next sample to be read, or  {@link C#INDEX_UNSET} if no samples remain.<p>The preferred choice is the sample with the smallest offset not requiring a source reload, or if not available the sample with the smallest overall offset to avoid subsequent source reloads. <p>To deal with poor sample interleaving, we also check whether the required memory to catch up with the next logical sample (based on sample time) exceeds  {@link #MAXIMUM_READ_AHEAD_BYTES_STREAM}. If this is the case, we continue with this sample even though it may require a source reload.
 */
private int getTrackIndexOfNextReadSample(long inputPosition){
  long preferredSkipAmount=Long.MAX_VALUE;
  boolean preferredRequiresReload=true;
  int preferredTrackIndex=C.INDEX_UNSET;
  long preferredAccumulatedBytes=Long.MAX_VALUE;
  long minAccumulatedBytes=Long.MAX_VALUE;
  boolean minAccumulatedBytesRequiresReload=true;
  int minAccumulatedBytesTrackIndex=C.INDEX_UNSET;
  for (int trackIndex=0; trackIndex < tracks.length; trackIndex++) {
    Mp4Track track=tracks[trackIndex];
    int sampleIndex=track.sampleIndex;
    if (sampleIndex == track.sampleTable.sampleCount) {
      continue;
    }
    long sampleOffset=track.sampleTable.offsets[sampleIndex];
    long sampleAccumulatedBytes=accumulatedSampleSizes[trackIndex][sampleIndex];
    long skipAmount=sampleOffset - inputPosition;
    boolean requiresReload=skipAmount < 0 || skipAmount >= RELOAD_MINIMUM_SEEK_DISTANCE;
    if ((!requiresReload && preferredRequiresReload) || (requiresReload == preferredRequiresReload && skipAmount < preferredSkipAmount)) {
      preferredRequiresReload=requiresReload;
      preferredSkipAmount=skipAmount;
      preferredTrackIndex=trackIndex;
      preferredAccumulatedBytes=sampleAccumulatedBytes;
    }
    if (sampleAccumulatedBytes < minAccumulatedBytes) {
      minAccumulatedBytes=sampleAccumulatedBytes;
      minAccumulatedBytesRequiresReload=requiresReload;
      minAccumulatedBytesTrackIndex=trackIndex;
    }
  }
  return minAccumulatedBytes == Long.MAX_VALUE || !minAccumulatedBytesRequiresReload || preferredAccumulatedBytes < minAccumulatedBytes + MAXIMUM_READ_AHEAD_BYTES_STREAM ? preferredTrackIndex : minAccumulatedBytesTrackIndex;
}
