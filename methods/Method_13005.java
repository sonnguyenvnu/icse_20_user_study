/** 
 * Create a regular expression to match package, type and inner type name. Rules: '*'        matches 0 ou N characters '?'        matches 1 character lower case matches insensitive case upper case matches upper case
 */
protected static Pattern createRegExpPattern(String pattern){
  int patternLength=pattern.length();
  StringBuilder sbPattern=new StringBuilder(patternLength * 4);
  for (int i=0; i < patternLength; i++) {
    char c=pattern.charAt(i);
    if (Character.isUpperCase(c)) {
      if (i > 1) {
        sbPattern.append(".*");
      }
      sbPattern.append(c);
    }
 else     if (Character.isLowerCase(c)) {
      sbPattern.append('[').append(c).append(Character.toUpperCase(c)).append(']');
    }
 else     if (c == '*') {
      sbPattern.append(".*");
    }
 else     if (c == '?') {
      sbPattern.append(".");
    }
 else {
      sbPattern.append(c);
    }
  }
  sbPattern.append(".*");
  return Pattern.compile(sbPattern.toString());
}
