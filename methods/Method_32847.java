@Override public boolean onCodePoint(final int codePoint,boolean ctrlDown,TerminalSession session){
  if (mVirtualFnKeyDown) {
    int resultingKeyCode=-1;
    int resultingCodePoint=-1;
    boolean altDown=false;
    int lowerCase=Character.toLowerCase(codePoint);
switch (lowerCase) {
case 'w':
      resultingKeyCode=KeyEvent.KEYCODE_DPAD_UP;
    break;
case 'a':
  resultingKeyCode=KeyEvent.KEYCODE_DPAD_LEFT;
break;
case 's':
resultingKeyCode=KeyEvent.KEYCODE_DPAD_DOWN;
break;
case 'd':
resultingKeyCode=KeyEvent.KEYCODE_DPAD_RIGHT;
break;
case 'p':
resultingKeyCode=KeyEvent.KEYCODE_PAGE_UP;
break;
case 'n':
resultingKeyCode=KeyEvent.KEYCODE_PAGE_DOWN;
break;
case 't':
resultingKeyCode=KeyEvent.KEYCODE_TAB;
break;
case 'i':
resultingKeyCode=KeyEvent.KEYCODE_INSERT;
break;
case 'h':
resultingCodePoint='~';
break;
case 'u':
resultingCodePoint='_';
break;
case 'l':
resultingCodePoint='|';
break;
case '1':
case '2':
case '3':
case '4':
case '5':
case '6':
case '7':
case '8':
case '9':
resultingKeyCode=(codePoint - '1') + KeyEvent.KEYCODE_F1;
break;
case '0':
resultingKeyCode=KeyEvent.KEYCODE_F10;
break;
case 'e':
resultingCodePoint=27;
break;
case '.':
resultingCodePoint=28;
break;
case 'b':
case 'f':
case 'x':
resultingCodePoint=lowerCase;
altDown=true;
break;
case 'v':
resultingCodePoint=-1;
AudioManager audio=(AudioManager)mActivity.getSystemService(Context.AUDIO_SERVICE);
audio.adjustSuggestedStreamVolume(AudioManager.ADJUST_SAME,AudioManager.USE_DEFAULT_STREAM_TYPE,AudioManager.FLAG_SHOW_UI);
break;
case 'q':
case 'k':
mActivity.toggleShowExtraKeys();
break;
}
if (resultingKeyCode != -1) {
TerminalEmulator term=session.getEmulator();
session.write(KeyHandler.getCode(resultingKeyCode,0,term.isCursorKeysApplicationMode(),term.isKeypadApplicationMode()));
}
 else if (resultingCodePoint != -1) {
session.writeCodePoint(altDown,resultingCodePoint);
}
return true;
}
 else if (ctrlDown) {
if (codePoint == 106 && !session.isRunning()) {
mActivity.removeFinishedSession(session);
return true;
}
List<TermuxPreferences.KeyboardShortcut> shortcuts=mActivity.mSettings.shortcuts;
if (!shortcuts.isEmpty()) {
int codePointLowerCase=Character.toLowerCase(codePoint);
for (int i=shortcuts.size() - 1; i >= 0; i--) {
TermuxPreferences.KeyboardShortcut shortcut=shortcuts.get(i);
if (codePointLowerCase == shortcut.codePoint) {
switch (shortcut.shortcutAction) {
case TermuxPreferences.SHORTCUT_ACTION_CREATE_SESSION:
mActivity.addNewSession(false,null);
return true;
case TermuxPreferences.SHORTCUT_ACTION_PREVIOUS_SESSION:
mActivity.switchToSession(false);
return true;
case TermuxPreferences.SHORTCUT_ACTION_NEXT_SESSION:
mActivity.switchToSession(true);
return true;
case TermuxPreferences.SHORTCUT_ACTION_RENAME_SESSION:
mActivity.renameSession(mActivity.getCurrentTermSession());
return true;
}
}
}
}
}
return false;
}
