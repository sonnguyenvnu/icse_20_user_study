/** 
 * Get out of keyboard mode. If there is nothing in typedTimes, revert to TimePicker's time.
 * @param changeDisplays If true, update the displays with the relevant time.
 */
private void finishKbMode(boolean updateDisplays){
  mInKbMode=false;
  if (!mTypedTimes.isEmpty()) {
    int values[]=getEnteredTime(null);
    mTimePicker.setTime(values[0],values[1]);
    if (!mIs24HourMode) {
      mTimePicker.setAmOrPm(values[2]);
    }
    mTypedTimes.clear();
  }
  if (updateDisplays) {
    updateDisplay(false);
    mTimePicker.trySettingInputEnabled(true);
  }
}
