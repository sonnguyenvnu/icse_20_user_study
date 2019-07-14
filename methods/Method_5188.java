/** 
 * Return uid of media source holder from period uid of concatenated source. 
 */
private static Object getMediaSourceHolderUid(Object periodUid){
  return ConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(periodUid);
}
