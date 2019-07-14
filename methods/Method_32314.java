/** 
 * Gets the JDK style code from the Joda code.
 * @param ch  the Joda style code
 * @return the JDK style code
 */
private static int selectStyle(char ch){
switch (ch) {
case 'S':
    return SHORT;
case 'M':
  return MEDIUM;
case 'L':
return LONG;
case 'F':
return FULL;
case '-':
return NONE;
default :
throw new IllegalArgumentException("Invalid style character: " + ch);
}
}
