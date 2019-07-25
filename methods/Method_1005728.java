/** 
 * <p>Removes one newline from end of a String if it's there, otherwise leave it alone.  A newline is &quot; {@code \n}&quot;, &quot; {@code \r}&quot;, or &quot; {@code \r\n}&quot;.</p> <p>NOTE: This method changed in 2.0. It now more closely matches Perl chomp.</p> <pre> StringUtils.chomp(null)          = null StringUtils.chomp("")            = "" StringUtils.chomp("abc \r")      = "abc " StringUtils.chomp("abc\n")       = "abc" StringUtils.chomp("abc\r\n")     = "abc" StringUtils.chomp("abc\r\n\r\n") = "abc\r\n" StringUtils.chomp("abc\n\r")     = "abc\n" StringUtils.chomp("abc\n\rabc")  = "abc\n\rabc" StringUtils.chomp("\r")          = "" StringUtils.chomp("\n")          = "" StringUtils.chomp("\r\n")        = "" </pre>
 * @param str  the String to chomp a newline from, may be null
 * @return String without newline, {@code null} if null String input
 */
public static String chomp(String str){
  if (isEmpty(str)) {
    return str;
  }
  if (str.length() == 1) {
    char ch=str.charAt(0);
    if (ch == CharUtils.CR || ch == CharUtils.LF) {
      return EMPTY;
    }
    return str;
  }
  int lastIdx=str.length() - 1;
  char last=str.charAt(lastIdx);
  if (last == CharUtils.LF) {
    if (str.charAt(lastIdx - 1) == CharUtils.CR) {
      lastIdx--;
    }
  }
 else   if (last != CharUtils.CR) {
    lastIdx++;
  }
  return str.substring(0,lastIdx);
}
