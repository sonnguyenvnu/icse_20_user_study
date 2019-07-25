public void clean(){
  long checkTime=resolveSegment(System.currentTimeMillis() - config.getDispatchLogKeepTime() - config.getCheckCleanTimeBeforeDispatch(),segmentScale);
  for (  DelaySegment<ScheduleSetSequence> segment : segments.values()) {
    if (segment.getSegmentBaseOffset() < checkTime) {
      clean(segment.getSegmentBaseOffset());
    }
  }
}
