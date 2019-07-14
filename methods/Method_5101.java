/** 
 * Returns UID of child timeline from a concatenated period UID.
 * @param concatenatedUid UID of a period in a concatenated timeline.
 * @return UID of the child timeline this period belongs to.
 */
public static Object getChildTimelineUidFromConcatenatedUid(Object concatenatedUid){
  return ((Pair<?,?>)concatenatedUid).first;
}
