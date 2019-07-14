/** 
 * Called by the picker for updating the header display.
 */
@Override public void onValueSelected(int pickerIndex,int newValue,boolean autoAdvance){
  if (pickerIndex == HOUR_INDEX) {
    setHour(newValue,false);
    String announcement=String.format("%d",newValue);
    if (mAllowAutoAdvance && autoAdvance) {
      setCurrentItemShowing(MINUTE_INDEX,true,true,false);
      announcement+=". " + mSelectMinutes;
    }
 else {
      mTimePicker.setContentDescription(mHourPickerDescription + ": " + newValue);
    }
    Utils.tryAccessibilityAnnounce(mTimePicker,announcement);
  }
 else   if (pickerIndex == MINUTE_INDEX) {
    setMinute(newValue);
    mTimePicker.setContentDescription(mMinutePickerDescription + ": " + newValue);
  }
 else   if (pickerIndex == AMPM_INDEX) {
    updateAmPmDisplay(newValue);
  }
 else   if (pickerIndex == ENABLE_PICKER_INDEX) {
    if (!isTypedTimeFullyLegal()) {
      mTypedTimes.clear();
    }
    finishKbMode(true);
  }
}
