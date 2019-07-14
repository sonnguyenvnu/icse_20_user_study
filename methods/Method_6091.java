private static long closestVsync(long releaseTime,long sampledVsyncTime,long vsyncDuration){
  long vsyncCount=(releaseTime - sampledVsyncTime) / vsyncDuration;
  long snappedTimeNs=sampledVsyncTime + (vsyncDuration * vsyncCount);
  long snappedBeforeNs;
  long snappedAfterNs;
  if (releaseTime <= snappedTimeNs) {
    snappedBeforeNs=snappedTimeNs - vsyncDuration;
    snappedAfterNs=snappedTimeNs;
  }
 else {
    snappedBeforeNs=snappedTimeNs;
    snappedAfterNs=snappedTimeNs + vsyncDuration;
  }
  long snappedAfterDiff=snappedAfterNs - releaseTime;
  long snappedBeforeDiff=releaseTime - snappedBeforeNs;
  return snappedAfterDiff < snappedBeforeDiff ? snappedAfterNs : snappedBeforeNs;
}
