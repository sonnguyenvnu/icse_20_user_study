/** 
 * <p>Centers a String in a larger String of size  {@code size}. Uses a supplied String as the value to pad the String with.</p> <p>If the size is less than the String length, the String is returned. A  {@code null} String returns {@code null}. A negative size is treated as zero.</p> <pre> StringUtils.center(null, *, *)     = null StringUtils.center("", 4, " ")     = "    " StringUtils.center("ab", -1, " ")  = "ab" StringUtils.center("ab", 4, " ")   = " ab" StringUtils.center("abcd", 2, " ") = "abcd" StringUtils.center("a", 4, " ")    = " a  " StringUtils.center("a", 4, "yz")   = "yayz" StringUtils.center("abc", 7, null) = "  abc  " StringUtils.center("abc", 7, "")   = "  abc  " </pre>
 * @param str  the String to center, may be null
 * @param size  the int size of new String, negative treated as zero
 * @param padStr  the String to pad the new String with, must not be null or empty
 * @return centered String, {@code null} if null String input
 * @throws IllegalArgumentException if padStr is {@code null} or empty
 */
public static String center(String str,int size,String padStr){
  if (str == null || size <= 0) {
    return str;
  }
  if (isEmpty(padStr)) {
    padStr=" ";
  }
  int strLen=str.length();
  int pads=size - strLen;
  if (pads <= 0) {
    return str;
  }
  str=leftPad(str,strLen + pads / 2,padStr);
  str=rightPad(str,size,padStr);
  return str;
}
