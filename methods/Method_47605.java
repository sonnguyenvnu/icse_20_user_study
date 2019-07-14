/** 
 * Try to start keyboard mode with the specified key, as long as the timepicker is not in the middle of a touch-event.
 * @param keyCode The key to use as the first press. Keyboard mode will not be started if thekey is not legal to start with. Or, pass in -1 to get into keyboard mode without a starting key.
 */
private void tryStartingKbMode(int keyCode){
  if (mTimePicker.trySettingInputEnabled(false) && (keyCode == -1 || addKeyIfLegal(keyCode))) {
    mInKbMode=true;
    mDoneButton.setEnabled(false);
    updateDisplay(false);
  }
}
