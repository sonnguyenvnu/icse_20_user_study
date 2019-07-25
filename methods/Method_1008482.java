@Override public List<Segment> segments(boolean verbose){
  try (ReleasableLock lock=readLock.acquire()){
    Segment[] segmentsArr=getSegmentInfo(lastCommittedSegmentInfos,verbose);
    Set<OnGoingMerge> onGoingMerges=mergeScheduler.onGoingMerges();
    for (    OnGoingMerge onGoingMerge : onGoingMerges) {
      for (      SegmentCommitInfo segmentInfoPerCommit : onGoingMerge.getMergedSegments()) {
        for (        Segment segment : segmentsArr) {
          if (segment.getName().equals(segmentInfoPerCommit.info.name)) {
            segment.mergeId=onGoingMerge.getId();
            break;
          }
        }
      }
    }
    return Arrays.asList(segmentsArr);
  }
 }
