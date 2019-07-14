/** 
 * Input the specified keyCode if applicable and return if the input was consumed. 
 */
public boolean handleKeyCode(int keyCode,int keyMod){
  TerminalEmulator term=mTermSession.getEmulator();
  String code=KeyHandler.getCode(keyCode,keyMod,term.isCursorKeysApplicationMode(),term.isKeypadApplicationMode());
  if (code == null)   return false;
  mTermSession.write(code);
  return true;
}
