/** 
 * Gets position of nth to last char in String. nthToLastCharIndex(1, "a.tar.gz") = 1 nthToLastCharIndex(0, "a.tar.gz") = 5
 */
public static int nthToLastCharIndex(int elementNumber,String str,char element){
  if (elementNumber <= 0)   throw new IllegalArgumentException();
  int occurencies=0;
  for (int i=str.length() - 1; i >= 0; i--) {
    if (str.charAt(i) == element && ++occurencies == elementNumber) {
      return i;
    }
  }
  return -1;
}
