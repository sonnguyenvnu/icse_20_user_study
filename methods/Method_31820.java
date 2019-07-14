/** 
 * Get this time interval as a <code>MutableInterval</code>. <p> This will always return a new <code>MutableInterval</code> with the same interval.
 * @return the time interval as a MutableInterval object
 */
public MutableInterval toMutableInterval(){
  return new MutableInterval(getStartMillis(),getEndMillis(),getChronology());
}
