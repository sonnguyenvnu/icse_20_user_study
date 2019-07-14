/** 
 * If the hours are showing, return the current hour. If the minutes are showing, return the current minute.
 */
private int getCurrentlyShowingValue(){
  int currentIndex=getCurrentItemShowing();
  if (currentIndex == HOUR_INDEX) {
    return mCurrentHoursOfDay;
  }
 else   if (currentIndex == MINUTE_INDEX) {
    return mCurrentMinutes;
  }
 else {
    return -1;
  }
}
