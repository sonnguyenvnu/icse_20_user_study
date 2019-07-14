protected int printDigit(int dcv,int lineNumber,int divisor,int left){
  if (digitCount >= dcv) {
    if (lineNumber < divisor) {
      stringBuffer.append(' ');
    }
 else {
      int e=(lineNumber - left) / divisor;
      stringBuffer.append((char)('0' + e));
      left+=e * divisor;
    }
  }
  return left;
}
