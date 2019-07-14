@Override public boolean onKeyDown(int keyCode,KeyEvent event){
  if (LOG_KEY_EVENTS)   Log.i(EmulatorDebug.LOG_TAG,"onKeyDown(keyCode=" + keyCode + ", isSystem()=" + event.isSystem() + ", event=" + event + ")");
  if (mEmulator == null)   return true;
  if (mClient.onKeyDown(keyCode,event,mTermSession)) {
    invalidate();
    return true;
  }
 else   if (event.isSystem() && (!mClient.shouldBackButtonBeMappedToEscape() || keyCode != KeyEvent.KEYCODE_BACK)) {
    return super.onKeyDown(keyCode,event);
  }
 else   if (event.getAction() == KeyEvent.ACTION_MULTIPLE && keyCode == KeyEvent.KEYCODE_UNKNOWN) {
    mTermSession.write(event.getCharacters());
    return true;
  }
  final int metaState=event.getMetaState();
  final boolean controlDownFromEvent=event.isCtrlPressed();
  final boolean leftAltDownFromEvent=(metaState & KeyEvent.META_ALT_LEFT_ON) != 0;
  final boolean rightAltDownFromEvent=(metaState & KeyEvent.META_ALT_RIGHT_ON) != 0;
  int keyMod=0;
  if (controlDownFromEvent)   keyMod|=KeyHandler.KEYMOD_CTRL;
  if (event.isAltPressed())   keyMod|=KeyHandler.KEYMOD_ALT;
  if (event.isShiftPressed())   keyMod|=KeyHandler.KEYMOD_SHIFT;
  if (!event.isFunctionPressed() && handleKeyCode(keyCode,keyMod)) {
    if (LOG_KEY_EVENTS)     Log.i(EmulatorDebug.LOG_TAG,"handleKeyCode() took key event");
    return true;
  }
  int bitsToClear=KeyEvent.META_CTRL_MASK;
  if (rightAltDownFromEvent) {
  }
 else {
    bitsToClear|=KeyEvent.META_ALT_ON | KeyEvent.META_ALT_LEFT_ON;
  }
  int effectiveMetaState=event.getMetaState() & ~bitsToClear;
  int result=event.getUnicodeChar(effectiveMetaState);
  if (LOG_KEY_EVENTS)   Log.i(EmulatorDebug.LOG_TAG,"KeyEvent#getUnicodeChar(" + effectiveMetaState + ") returned: " + result);
  if (result == 0) {
    return false;
  }
  int oldCombiningAccent=mCombiningAccent;
  if ((result & KeyCharacterMap.COMBINING_ACCENT) != 0) {
    if (mCombiningAccent != 0)     inputCodePoint(mCombiningAccent,controlDownFromEvent,leftAltDownFromEvent);
    mCombiningAccent=result & KeyCharacterMap.COMBINING_ACCENT_MASK;
  }
 else {
    if (mCombiningAccent != 0) {
      int combinedChar=KeyCharacterMap.getDeadChar(mCombiningAccent,result);
      if (combinedChar > 0)       result=combinedChar;
      mCombiningAccent=0;
    }
    inputCodePoint(result,controlDownFromEvent,leftAltDownFromEvent);
  }
  if (mCombiningAccent != oldCombiningAccent)   invalidate();
  return true;
}
