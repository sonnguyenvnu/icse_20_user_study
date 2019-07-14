/** 
 * Set either the hour or the minute. Will set the internal value, and set the selection.
 */
private void setItem(int index,int value){
  if (index == HOUR_INDEX) {
    setValueForItem(HOUR_INDEX,value);
    int hourDegrees=(value % 12) * HOUR_VALUE_TO_DEGREES_STEP_SIZE;
    mHourRadialSelectorView.setSelection(hourDegrees,isHourInnerCircle(value),false);
    mHourRadialSelectorView.invalidate();
  }
 else   if (index == MINUTE_INDEX) {
    setValueForItem(MINUTE_INDEX,value);
    int minuteDegrees=value * MINUTE_VALUE_TO_DEGREES_STEP_SIZE;
    mMinuteRadialSelectorView.setSelection(minuteDegrees,false,false);
    mMinuteRadialSelectorView.invalidate();
  }
}
