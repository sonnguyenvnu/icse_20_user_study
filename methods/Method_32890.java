/** 
 * When in  {@link #ESC_P} ("device control") sequence. 
 */
private void doDeviceControl(int b){
switch (b) {
case (byte)'\\':
{
      String dcs=mOSCOrDeviceControlArgs.toString();
      if (dcs.startsWith("$q")) {
        if (dcs.equals("$q\"p")) {
          String csiString="64;1\"p";
          mSession.write("\033P1$r" + csiString + "\033\\");
        }
 else {
          finishSequenceAndLogError("Unrecognized DECRQSS string: '" + dcs + "'");
        }
      }
 else       if (dcs.startsWith("+q")) {
        for (        String part : dcs.substring(2).split(";")) {
          if (part.length() % 2 == 0) {
            StringBuilder transBuffer=new StringBuilder();
            for (int i=0; i < part.length(); i+=2) {
              char c=(char)Long.decode("0x" + part.charAt(i) + "" + part.charAt(i + 1)).longValue();
              transBuffer.append(c);
            }
            String trans=transBuffer.toString();
            String responseValue;
switch (trans) {
case "Co":
case "colors":
              responseValue="256";
            break;
case "TN":
case "name":
          responseValue="xterm";
        break;
default :
      responseValue=KeyHandler.getCodeFromTermcap(trans,isDecsetInternalBitSet(DECSET_BIT_APPLICATION_CURSOR_KEYS),isDecsetInternalBitSet(DECSET_BIT_APPLICATION_KEYPAD));
    break;
}
if (responseValue == null) {
switch (trans) {
case "%1":
case "&8":
    break;
default :
  Log.w(EmulatorDebug.LOG_TAG,"Unhandled termcap/terminfo name: '" + trans + "'");
}
mSession.write("\033P0+r" + part + "\033\\");
}
 else {
StringBuilder hexEncoded=new StringBuilder();
for (int j=0; j < responseValue.length(); j++) {
hexEncoded.append(String.format("%02X",(int)responseValue.charAt(j)));
}
mSession.write("\033P1+r" + part + "=" + hexEncoded + "\033\\");
}
}
 else {
Log.e(EmulatorDebug.LOG_TAG,"Invalid device termcap/terminfo name of odd length: " + part);
}
}
}
 else {
if (LOG_ESCAPE_SEQUENCES) Log.e(EmulatorDebug.LOG_TAG,"Unrecognized device control string: " + dcs);
}
finishSequence();
}
break;
default :
if (mOSCOrDeviceControlArgs.length() > MAX_OSC_STRING_LENGTH) {
mOSCOrDeviceControlArgs.setLength(0);
finishSequence();
}
 else {
mOSCOrDeviceControlArgs.appendCodePoint(b);
continueSequence(mEscapeState);
}
}
}
