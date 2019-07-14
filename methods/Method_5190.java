private static Object getPeriodUid(MediaSourceHolder holder,Object childPeriodUid){
  if (holder.timeline.replacedId.equals(childPeriodUid)) {
    childPeriodUid=DeferredTimeline.DUMMY_ID;
  }
  return ConcatenatedTimeline.getConcatenatedUid(holder.uid,childPeriodUid);
}
