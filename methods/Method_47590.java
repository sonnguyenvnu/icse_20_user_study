/** 
 * Check if a given hour appears in the outer circle or the inner circle
 * @return true if the hour is in the inner circle, false if it's in the outer circle.
 */
private boolean isHourInnerCircle(int hourOfDay){
  return mIs24HourMode && (hourOfDay <= 12 && hourOfDay != 0);
}
