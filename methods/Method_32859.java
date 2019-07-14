static String getCodeFromTermcap(String termcap,boolean cursorKeysApplication,boolean keypadApplication){
  Integer keyCodeAndMod=TERMCAP_TO_KEYCODE.get(termcap);
  if (keyCodeAndMod == null)   return null;
  int keyCode=keyCodeAndMod;
  int keyMod=0;
  if ((keyCode & KEYMOD_SHIFT) != 0) {
    keyMod|=KEYMOD_SHIFT;
    keyCode&=~KEYMOD_SHIFT;
  }
  if ((keyCode & KEYMOD_CTRL) != 0) {
    keyMod|=KEYMOD_CTRL;
    keyCode&=~KEYMOD_CTRL;
  }
  if ((keyCode & KEYMOD_ALT) != 0) {
    keyMod|=KEYMOD_ALT;
    keyCode&=~KEYMOD_ALT;
  }
  return getCode(keyCode,keyMod,cursorKeysApplication,keypadApplication);
}
