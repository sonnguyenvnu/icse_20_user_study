/** 
 * <p> Return true, if wday (see Calendar.get()) is defined to be exluded. E. g. saturday and sunday. </p>
 */
public boolean isDayExcluded(int wday){
  return excludeDays[wday];
}
