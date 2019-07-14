/** 
 * Count the occurrences of the substring  {@code sub} in string {@code str}.
 * @param str string to search in
 * @param sub string to search for
 */
public static int countOccurrencesOf(String str,String sub){
  if (!hasLength(str) || !hasLength(sub)) {
    return 0;
  }
  int count=0;
  int pos=0;
  int idx;
  while ((idx=str.indexOf(sub,pos)) != -1) {
    ++count;
    pos=idx + sub.length();
  }
  return count;
}
