/** 
 * Returns cloned HotkeyData instance.
 * @return cloned HotkeyData instance
 */
@Override protected HotkeyData clone(){
  return new HotkeyData(isCtrl(),isAlt(),isShift(),getKeyCode());
}
