private void collectOSCArgs(int b){
  if (mOSCOrDeviceControlArgs.length() < MAX_OSC_STRING_LENGTH) {
    mOSCOrDeviceControlArgs.appendCodePoint(b);
    continueSequence(mEscapeState);
  }
 else {
    unknownSequence(b);
  }
}
