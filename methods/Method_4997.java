/** 
 * Returns the number of periods for which media is available. Must not be called until after preparation completes.
 */
public final int getPeriodCount(){
  Assertions.checkNotNull(trackGroupArrays);
  return trackGroupArrays.length;
}
