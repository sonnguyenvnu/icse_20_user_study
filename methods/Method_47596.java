/** 
 * Set touch input as enabled or disabled, for use with keyboard mode.
 */
public boolean trySettingInputEnabled(boolean inputEnabled){
  if (mDoingTouch && !inputEnabled) {
    return false;
  }
  mInputEnabled=inputEnabled;
  mGrayBox.setVisibility(inputEnabled ? View.INVISIBLE : View.VISIBLE);
  return true;
}
