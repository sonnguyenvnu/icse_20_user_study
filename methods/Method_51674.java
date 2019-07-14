/** 
 * @param buf
 * @param src
 * @param supportUTF8 override the default setting, whether special characters should be replaced with entities (<code>false</code>) or should be included as is ( <code>true</code>).
 */
public static void appendXmlEscaped(StringBuilder buf,String src,boolean supportUTF8){
  char c;
  int i=0;
  while (i < src.length()) {
    c=src.charAt(i++);
    if (c > '~') {
      if (!supportUTF8) {
        int codepoint=c;
        if (Character.isHighSurrogate(c)) {
          char low=src.charAt(i++);
          codepoint=Character.toCodePoint(c,low);
        }
        buf.append("&#x").append(Integer.toHexString(codepoint)).append(';');
      }
 else {
        buf.append(c);
      }
    }
 else     if (c == '&') {
      buf.append("&amp;");
    }
 else     if (c == '"') {
      buf.append("&quot;");
    }
 else     if (c == '<') {
      buf.append("&lt;");
    }
 else     if (c == '>') {
      buf.append("&gt;");
    }
 else {
      buf.append(c);
    }
  }
}
