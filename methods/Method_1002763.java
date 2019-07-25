/** 
 * Formats the given epoch time in milliseconds to typical human-readable format "yyyy-MM-dd'T'HH:mm:ss.SSSX".
 * @deprecated Use {@link #epochMillis(long)}.
 * @param timeMillis epoch time in milliseconds
 * @return the human readable string representation of the given epoch time
 */
@Deprecated public static StringBuilder epoch(long timeMillis){
  final StringBuilder buf=new StringBuilder(45);
  appendEpochMillis(buf,timeMillis);
  return buf;
}
