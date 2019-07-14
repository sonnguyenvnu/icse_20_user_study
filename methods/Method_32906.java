/** 
 * Process the next ASCII character of a parameter. 
 */
private void parseArg(int b){
  if (b >= '0' && b <= '9') {
    if (mArgIndex < mArgs.length) {
      int oldValue=mArgs[mArgIndex];
      int thisDigit=b - '0';
      int value;
      if (oldValue >= 0) {
        value=oldValue * 10 + thisDigit;
      }
 else {
        value=thisDigit;
      }
      mArgs[mArgIndex]=value;
    }
    continueSequence(mEscapeState);
  }
 else   if (b == ';') {
    if (mArgIndex < mArgs.length) {
      mArgIndex++;
    }
    continueSequence(mEscapeState);
  }
 else {
    unknownSequence(b);
  }
}
