/** 
 * @param fromIndexInclusive low endpoint (inclusive) of the sublist of requests' history.
 * @param toIndexExclusive high endpoint (exclusive) of the sublist of requests' history.
 * @return specified range of requests' history (from oldest to newest).
 */
public static List<RecordedRequest> take(int fromIndexInclusive,int toIndexExclusive){
  return dispatcher.getRequestHistory().subList(fromIndexInclusive,toIndexExclusive);
}
