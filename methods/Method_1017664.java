/** 
 * Calculate the position of the next comma or space or negative sign
 * @param s      the string to search
 * @param start  the position to start searching
 * @param result the result of the extraction, including the position of thethe starting position of next number, whether it is ending with a '-'.
 */
private static void extract(String s,int start,ExtractFloatResult result){
  int currentIndex=start;
  boolean foundSeparator=false;
  result.mEndWithNegOrDot=false;
  boolean secondDot=false;
  boolean isExponential=false;
  for (; currentIndex < s.length(); currentIndex++) {
    boolean isPrevExponential=isExponential;
    isExponential=false;
    char currentChar=s.charAt(currentIndex);
switch (currentChar) {
case ' ':
case ',':
      foundSeparator=true;
    break;
case '-':
  if (currentIndex != start && !isPrevExponential) {
    foundSeparator=true;
    result.mEndWithNegOrDot=true;
  }
break;
case '.':
if (!secondDot) {
secondDot=true;
}
 else {
foundSeparator=true;
result.mEndWithNegOrDot=true;
}
break;
case 'e':
case 'E':
isExponential=true;
break;
}
if (foundSeparator) {
break;
}
}
result.mEndPosition=currentIndex;
}
