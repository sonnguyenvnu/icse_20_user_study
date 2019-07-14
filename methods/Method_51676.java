/** 
 * Determine the maximum number of common leading whitespace characters the strings share in the same sequence. Useful for determining how many leading characters can be removed to shift all the text in the strings to the left without misaligning them.
 * @param strings String[]
 * @return int
 */
public static int maxCommonLeadingWhitespaceForAll(String[] strings){
  int shortest=lengthOfShortestIn(strings);
  if (shortest == 0) {
    return 0;
  }
  char[] matches=new char[shortest];
  String str;
  for (int m=0; m < matches.length; m++) {
    matches[m]=strings[0].charAt(m);
    if (!Character.isWhitespace(matches[m])) {
      return m;
    }
    for (int i=0; i < strings.length; i++) {
      str=strings[i];
      if (str.charAt(m) != matches[m]) {
        return m;
      }
    }
  }
  return shortest;
}
