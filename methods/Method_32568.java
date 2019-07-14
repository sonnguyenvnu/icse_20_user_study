/** 
 * Sets the chronology of this time interval.
 * @param chrono  the chronology to use, null means ISO default
 */
public void setChronology(Chronology chrono){
  super.setInterval(getStartMillis(),getEndMillis(),chrono);
}
