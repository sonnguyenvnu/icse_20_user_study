public static String getCode(int keyCode,int keyMode,boolean cursorApp,boolean keypadApplication){
switch (keyCode) {
case KEYCODE_DPAD_CENTER:
    return "\015";
case KEYCODE_DPAD_UP:
  return (keyMode == 0) ? (cursorApp ? "\033OA" : "\033[A") : transformForModifiers("\033[1",keyMode,'A');
case KEYCODE_DPAD_DOWN:
return (keyMode == 0) ? (cursorApp ? "\033OB" : "\033[B") : transformForModifiers("\033[1",keyMode,'B');
case KEYCODE_DPAD_RIGHT:
return (keyMode == 0) ? (cursorApp ? "\033OC" : "\033[C") : transformForModifiers("\033[1",keyMode,'C');
case KEYCODE_DPAD_LEFT:
return (keyMode == 0) ? (cursorApp ? "\033OD" : "\033[D") : transformForModifiers("\033[1",keyMode,'D');
case KEYCODE_MOVE_HOME:
return (keyMode == 0) ? (cursorApp ? "\033OH" : "\033[H") : transformForModifiers("\033[1",keyMode,'H');
case KEYCODE_MOVE_END:
return (keyMode == 0) ? (cursorApp ? "\033OF" : "\033[F") : transformForModifiers("\033[1",keyMode,'F');
case KEYCODE_F1:
return (keyMode == 0) ? "\033OP" : transformForModifiers("\033[1",keyMode,'P');
case KEYCODE_F2:
return (keyMode == 0) ? "\033OQ" : transformForModifiers("\033[1",keyMode,'Q');
case KEYCODE_F3:
return (keyMode == 0) ? "\033OR" : transformForModifiers("\033[1",keyMode,'R');
case KEYCODE_F4:
return (keyMode == 0) ? "\033OS" : transformForModifiers("\033[1",keyMode,'S');
case KEYCODE_F5:
return transformForModifiers("\033[15",keyMode,'~');
case KEYCODE_F6:
return transformForModifiers("\033[17",keyMode,'~');
case KEYCODE_F7:
return transformForModifiers("\033[18",keyMode,'~');
case KEYCODE_F8:
return transformForModifiers("\033[19",keyMode,'~');
case KEYCODE_F9:
return transformForModifiers("\033[20",keyMode,'~');
case KEYCODE_F10:
return transformForModifiers("\033[21",keyMode,'~');
case KEYCODE_F11:
return transformForModifiers("\033[23",keyMode,'~');
case KEYCODE_F12:
return transformForModifiers("\033[24",keyMode,'~');
case KEYCODE_SYSRQ:
return "\033[32~";
case KEYCODE_BREAK:
return "\033[34~";
case KEYCODE_ESCAPE:
case KEYCODE_BACK:
return "\033";
case KEYCODE_INSERT:
return transformForModifiers("\033[2",keyMode,'~');
case KEYCODE_FORWARD_DEL:
return transformForModifiers("\033[3",keyMode,'~');
case KEYCODE_PAGE_UP:
return "\033[5~";
case KEYCODE_PAGE_DOWN:
return "\033[6~";
case KEYCODE_DEL:
String prefix=((keyMode & KEYMOD_ALT) == 0) ? "" : "\033";
return prefix + (((keyMode & KEYMOD_CTRL) == 0) ? "\u007F" : "\u0008");
case KEYCODE_NUM_LOCK:
return "\033OP";
case KEYCODE_SPACE:
return ((keyMode & KEYMOD_CTRL) == 0) ? null : "\0";
case KEYCODE_TAB:
return (keyMode & KEYMOD_SHIFT) == 0 ? "\011" : "\033[Z";
case KEYCODE_ENTER:
return ((keyMode & KEYMOD_ALT) == 0) ? "\r" : "\033\r";
case KEYCODE_NUMPAD_ENTER:
return keypadApplication ? transformForModifiers("\033O",keyMode,'M') : "\n";
case KEYCODE_NUMPAD_MULTIPLY:
return keypadApplication ? transformForModifiers("\033O",keyMode,'j') : "*";
case KEYCODE_NUMPAD_ADD:
return keypadApplication ? transformForModifiers("\033O",keyMode,'k') : "+";
case KEYCODE_NUMPAD_COMMA:
return ",";
case KEYCODE_NUMPAD_DOT:
return keypadApplication ? "\033On" : ".";
case KEYCODE_NUMPAD_SUBTRACT:
return keypadApplication ? transformForModifiers("\033O",keyMode,'m') : "-";
case KEYCODE_NUMPAD_DIVIDE:
return keypadApplication ? transformForModifiers("\033O",keyMode,'o') : "/";
case KEYCODE_NUMPAD_0:
return keypadApplication ? transformForModifiers("\033O",keyMode,'p') : "0";
case KEYCODE_NUMPAD_1:
return keypadApplication ? transformForModifiers("\033O",keyMode,'q') : "1";
case KEYCODE_NUMPAD_2:
return keypadApplication ? transformForModifiers("\033O",keyMode,'r') : "2";
case KEYCODE_NUMPAD_3:
return keypadApplication ? transformForModifiers("\033O",keyMode,'s') : "3";
case KEYCODE_NUMPAD_4:
return keypadApplication ? transformForModifiers("\033O",keyMode,'t') : "4";
case KEYCODE_NUMPAD_5:
return keypadApplication ? transformForModifiers("\033O",keyMode,'u') : "5";
case KEYCODE_NUMPAD_6:
return keypadApplication ? transformForModifiers("\033O",keyMode,'v') : "6";
case KEYCODE_NUMPAD_7:
return keypadApplication ? transformForModifiers("\033O",keyMode,'w') : "7";
case KEYCODE_NUMPAD_8:
return keypadApplication ? transformForModifiers("\033O",keyMode,'x') : "8";
case KEYCODE_NUMPAD_9:
return keypadApplication ? transformForModifiers("\033O",keyMode,'y') : "9";
case KEYCODE_NUMPAD_EQUALS:
return keypadApplication ? transformForModifiers("\033O",keyMode,'X') : "=";
}
return null;
}
