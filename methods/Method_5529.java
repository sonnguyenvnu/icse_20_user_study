private void handleC0Command(int command){
switch (command) {
case COMMAND_NUL:
    break;
case COMMAND_ETX:
  cues=getDisplayCues();
break;
case COMMAND_BS:
currentCueBuilder.backspace();
break;
case COMMAND_FF:
resetCueBuilders();
break;
case COMMAND_CR:
currentCueBuilder.append('\n');
break;
case COMMAND_HCR:
break;
default :
if (command >= COMMAND_EXT1_START && command <= COMMAND_EXT1_END) {
Log.w(TAG,"Currently unsupported COMMAND_EXT1 Command: " + command);
serviceBlockPacket.skipBits(8);
}
 else if (command >= COMMAND_P16_START && command <= COMMAND_P16_END) {
Log.w(TAG,"Currently unsupported COMMAND_P16 Command: " + command);
serviceBlockPacket.skipBits(16);
}
 else {
Log.w(TAG,"Invalid C0 command: " + command);
}
}
}
