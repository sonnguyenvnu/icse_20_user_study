/** 
 * Compare digits at certain position in two strings. The longest run of digits wins. That aside, the greatest value wins.
 * @return if numbers are different, only 1 element is returned.
 */
protected int[] compareDigits(final String str1,int ndx1,final String str2,int ndx2){
  int zeroCount1=0;
  while (charAt(str1,ndx1) == '0') {
    zeroCount1++;
    ndx1++;
  }
  int len1=0;
  while (true) {
    final char char1=charAt(str1,ndx1);
    final boolean isDigitChar1=CharUtil.isDigit(char1);
    if (!isDigitChar1) {
      break;
    }
    len1++;
    ndx1++;
  }
  int zeroCount2=0;
  while (charAt(str2,ndx2) == '0') {
    zeroCount2++;
    ndx2++;
  }
  int len2=0;
  int ndx1_new=ndx1 - len1;
  int equalNumbers=0;
  while (true) {
    final char char2=charAt(str2,ndx2);
    final boolean isDigitChar2=CharUtil.isDigit(char2);
    if (!isDigitChar2) {
      break;
    }
    if (equalNumbers == 0 && (ndx1_new < ndx1)) {
      equalNumbers=charAt(str1,ndx1_new++) - char2;
    }
    len2++;
    ndx2++;
  }
  if (len1 != len2) {
    return new int[]{len1 - len2};
  }
  if (equalNumbers != 0) {
    return new int[]{equalNumbers};
  }
  return new int[]{0,zeroCount1 - zeroCount2,ndx1,ndx2};
}
