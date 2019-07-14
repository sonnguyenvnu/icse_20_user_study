/** 
 * Returns UID of the period in the child timeline from a concatenated period UID.
 * @param concatenatedUid UID of a period in a concatenated timeline.
 * @return UID of the period in the child timeline.
 */
public static Object getChildPeriodUidFromConcatenatedUid(Object concatenatedUid){
  return ((Pair<?,?>)concatenatedUid).second;
}
