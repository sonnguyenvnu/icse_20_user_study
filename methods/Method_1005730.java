/** 
 * <p>Remove the last character from a String.</p> <p>If the String ends in  {@code \r\n}, then remove both of them.</p> <pre> StringUtils.chop(null)          = null StringUtils.chop("")            = "" StringUtils.chop("abc \r")      = "abc " StringUtils.chop("abc\n")       = "abc" StringUtils.chop("abc\r\n")     = "abc" StringUtils.chop("abc")         = "ab" StringUtils.chop("abc\nabc")    = "abc\nab" StringUtils.chop("a")           = "" StringUtils.chop("\r")          = "" StringUtils.chop("\n")          = "" StringUtils.chop("\r\n")        = "" </pre>
 * @param str  the String to chop last character from, may be null
 * @return String without last character, {@code null} if null String input
 */
public static String chop(String str){
  if (str == null) {
    return null;
  }
  int strLen=str.length();
  if (strLen < 2) {
    return EMPTY;
  }
  int lastIdx=strLen - 1;
  String ret=str.substring(0,lastIdx);
  char last=str.charAt(lastIdx);
  if (last == CharUtils.LF && ret.charAt(lastIdx - 1) == CharUtils.CR) {
    return ret.substring(0,lastIdx - 1);
  }
  return ret;
}
