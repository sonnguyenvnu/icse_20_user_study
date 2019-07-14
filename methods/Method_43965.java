/** 
 * Creates a date from a long representing milliseconds from epoch
 * @param millisecondsFromEpoch
 * @return the Date object
 */
public static Date fromMillisUtc(long millisecondsFromEpoch){
  return new Date(millisecondsFromEpoch);
}
