/** 
 * Return uid of child period from period uid of concatenated source. 
 */
private static Object getChildPeriodUid(MediaSourceHolder holder,Object periodUid){
  Object childUid=ConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(periodUid);
  return childUid.equals(DeferredTimeline.DUMMY_ID) ? holder.timeline.replacedId : childUid;
}
