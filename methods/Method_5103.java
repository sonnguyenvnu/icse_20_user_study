/** 
 * Returns concatenated UID for a period in a child timeline.
 * @param childTimelineUid UID of the child timeline this period belongs to.
 * @param childPeriodUid UID of the period in the child timeline.
 * @return UID of the period in the concatenated timeline.
 */
public static Object getConcatenatedUid(Object childTimelineUid,Object childPeriodUid){
  return Pair.create(childTimelineUid,childPeriodUid);
}
