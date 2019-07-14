/** 
 * For each sample of each track, calculates accumulated size of all samples which need to be read before this sample can be used.
 */
private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] tracks){
  long[][] accumulatedSampleSizes=new long[tracks.length][];
  int[] nextSampleIndex=new int[tracks.length];
  long[] nextSampleTimesUs=new long[tracks.length];
  boolean[] tracksFinished=new boolean[tracks.length];
  for (int i=0; i < tracks.length; i++) {
    accumulatedSampleSizes[i]=new long[tracks[i].sampleTable.sampleCount];
    nextSampleTimesUs[i]=tracks[i].sampleTable.timestampsUs[0];
  }
  long accumulatedSampleSize=0;
  int finishedTracks=0;
  while (finishedTracks < tracks.length) {
    long minTimeUs=Long.MAX_VALUE;
    int minTimeTrackIndex=-1;
    for (int i=0; i < tracks.length; i++) {
      if (!tracksFinished[i] && nextSampleTimesUs[i] <= minTimeUs) {
        minTimeTrackIndex=i;
        minTimeUs=nextSampleTimesUs[i];
      }
    }
    int trackSampleIndex=nextSampleIndex[minTimeTrackIndex];
    accumulatedSampleSizes[minTimeTrackIndex][trackSampleIndex]=accumulatedSampleSize;
    accumulatedSampleSize+=tracks[minTimeTrackIndex].sampleTable.sizes[trackSampleIndex];
    nextSampleIndex[minTimeTrackIndex]=++trackSampleIndex;
    if (trackSampleIndex < accumulatedSampleSizes[minTimeTrackIndex].length) {
      nextSampleTimesUs[minTimeTrackIndex]=tracks[minTimeTrackIndex].sampleTable.timestampsUs[trackSampleIndex];
    }
 else {
      tracksFinished[minTimeTrackIndex]=true;
      finishedTracks++;
    }
  }
  return accumulatedSampleSizes;
}
