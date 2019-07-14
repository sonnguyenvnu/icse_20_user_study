/** 
 * Parses JSON numbers.
 */
protected Number parseNumber(){
  final int startIndex=ndx;
  char c=input[ndx];
  boolean isDouble=false;
  boolean isExp=false;
  if (c == '-') {
    ndx++;
  }
  while (true) {
    if (isEOF()) {
      break;
    }
    c=input[ndx];
    if (c >= '0' && c <= '9') {
      ndx++;
      continue;
    }
    if (c <= 32) {
      break;
    }
    if (c == ',' || c == '}' || c == ']') {
      break;
    }
    if (c == '.') {
      isDouble=true;
    }
 else     if (c == 'e' || c == 'E') {
      isExp=true;
    }
    ndx++;
  }
  final String value=new String(input,startIndex,ndx - startIndex);
  if (isDouble) {
    return Double.valueOf(value);
  }
  long longNumber;
  if (isExp) {
    longNumber=Double.valueOf(value).longValue();
  }
 else {
    if (value.length() >= 19) {
      BigInteger bigInteger=new BigInteger(value);
      if (isGreaterThanLong(bigInteger)) {
        return bigInteger;
      }
      longNumber=bigInteger.longValue();
    }
 else {
      longNumber=Long.parseLong(value);
    }
  }
  if ((longNumber >= Integer.MIN_VALUE) && (longNumber <= Integer.MAX_VALUE)) {
    return (int)longNumber;
  }
  return longNumber;
}
