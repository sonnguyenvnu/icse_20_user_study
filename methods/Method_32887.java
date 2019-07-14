/** 
 * If mouse events are being sent as escape codes to the terminal. 
 */
public boolean isMouseTrackingActive(){
  return isDecsetInternalBitSet(DECSET_BIT_MOUSE_TRACKING_PRESS_RELEASE) || isDecsetInternalBitSet(DECSET_BIT_MOUSE_TRACKING_BUTTON_EVENT);
}
