/** 
 * Convert <code>%</code><i>hh</i> sequences to single characters, and convert plus to space.
 * @param string A string that may contain<code>+</code>&nbsp;<small>(plus)</small> and <code>%</code><i>hh</i> sequences.
 * @return The unescaped string.
 */
public static String unescape(String string){
  int length=string.length();
  StringBuilder sb=new StringBuilder(length);
  for (int i=0; i < length; ++i) {
    char c=string.charAt(i);
    if (c == '+') {
      c=' ';
    }
 else     if (c == '%' && i + 2 < length) {
      int d=JSONTokener.dehexchar(string.charAt(i + 1));
      int e=JSONTokener.dehexchar(string.charAt(i + 2));
      if (d >= 0 && e >= 0) {
        c=(char)(d * 16 + e);
        i+=2;
      }
    }
    sb.append(c);
  }
  return sb.toString();
}
