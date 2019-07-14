/** 
 * Create a simple regular expression Rules: '*'        matchTypeEntries 0 ou N characters '?'        matchTypeEntries 1 character
 */
protected static Pattern createPattern(String pattern){
  int patternLength=pattern.length();
  StringBuilder sbPattern=new StringBuilder(patternLength * 2);
  for (int i=0; i < patternLength; i++) {
    char c=pattern.charAt(i);
    if (c == '*') {
      sbPattern.append(".*");
    }
 else     if (c == '?') {
      sbPattern.append('.');
    }
 else     if (c == '.') {
      sbPattern.append("\\.");
    }
 else {
      sbPattern.append(c);
    }
  }
  sbPattern.append(".*");
  return Pattern.compile(sbPattern.toString());
}
