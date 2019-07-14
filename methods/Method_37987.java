/** 
 * Count substring occurrences in a source string, ignoring case.
 * @param source	source string
 * @param sub		substring to count
 * @return			number of substring occurrences
 */
public static int countIgnoreCase(final String source,final String sub){
  int count=0;
  int j=0;
  int sublen=sub.length();
  if (sublen == 0) {
    return 0;
  }
  while (true) {
    int i=indexOfIgnoreCase(source,sub,j);
    if (i == -1) {
      break;
    }
    count++;
    j=i + sublen;
  }
  return count;
}
