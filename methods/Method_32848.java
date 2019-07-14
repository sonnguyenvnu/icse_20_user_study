/** 
 * Handle dedicated volume buttons as virtual keys if applicable. 
 */
private boolean handleVirtualKeys(int keyCode,KeyEvent event,boolean down){
  InputDevice inputDevice=event.getDevice();
  if (inputDevice != null && inputDevice.getKeyboardType() == InputDevice.KEYBOARD_TYPE_ALPHABETIC) {
    return false;
  }
 else   if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
    mVirtualControlKeyDown=down;
    return true;
  }
 else   if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
    mVirtualFnKeyDown=down;
    return true;
  }
  return false;
}
