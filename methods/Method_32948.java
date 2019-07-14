void inputCodePoint(int codePoint,boolean controlDownFromEvent,boolean leftAltDownFromEvent){
  if (LOG_KEY_EVENTS) {
    Log.i(EmulatorDebug.LOG_TAG,"inputCodePoint(codePoint=" + codePoint + ", controlDownFromEvent=" + controlDownFromEvent + ", leftAltDownFromEvent=" + leftAltDownFromEvent + ")");
  }
  if (mTermSession == null)   return;
  final boolean controlDown=controlDownFromEvent || mClient.readControlKey();
  final boolean altDown=leftAltDownFromEvent || mClient.readAltKey();
  if (mClient.onCodePoint(codePoint,controlDown,mTermSession))   return;
  if (controlDown) {
    if (codePoint >= 'a' && codePoint <= 'z') {
      codePoint=codePoint - 'a' + 1;
    }
 else     if (codePoint >= 'A' && codePoint <= 'Z') {
      codePoint=codePoint - 'A' + 1;
    }
 else     if (codePoint == ' ' || codePoint == '2') {
      codePoint=0;
    }
 else     if (codePoint == '[' || codePoint == '3') {
      codePoint=27;
    }
 else     if (codePoint == '\\' || codePoint == '4') {
      codePoint=28;
    }
 else     if (codePoint == ']' || codePoint == '5') {
      codePoint=29;
    }
 else     if (codePoint == '^' || codePoint == '6') {
      codePoint=30;
    }
 else     if (codePoint == '_' || codePoint == '7' || codePoint == '/') {
      codePoint=31;
    }
 else     if (codePoint == '8') {
      codePoint=127;
    }
  }
  if (codePoint > -1) {
switch (codePoint) {
case 0x02DC:
      codePoint=0x007E;
    break;
case 0x02CB:
  codePoint=0x0060;
break;
case 0x02C6:
codePoint=0x005E;
break;
}
mTermSession.writeCodePoint(altDown,codePoint);
}
}
