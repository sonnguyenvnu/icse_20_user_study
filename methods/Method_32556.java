/** 
 * Set the week of weekyear to the specified value.
 * @param weekOfWeekyear the week of the weekyear
 * @throws IllegalArgumentException if the value is invalid
 */
public void setWeekOfWeekyear(final int weekOfWeekyear){
  setMillis(getChronology().weekOfWeekyear().set(getMillis(),weekOfWeekyear));
}
