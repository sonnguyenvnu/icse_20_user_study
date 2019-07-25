/** 
 * ??
 * @param s
 * @return
 */
public static String encode(String s){
  StringBuilder sbuf=new StringBuilder();
  int len=s.length();
  for (int i=0; i < len; i++) {
    int ch=s.charAt(i);
    if ('A' <= ch && ch <= 'Z') {
      sbuf.append((char)ch);
    }
 else     if ('a' <= ch && ch <= 'z') {
      sbuf.append((char)ch);
    }
 else     if ('0' <= ch && ch <= '9') {
      sbuf.append((char)ch);
    }
 else     if (ch == '-' || ch == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
      sbuf.append((char)ch);
    }
 else     if (ch <= 0x007F) {
      sbuf.append('%');
      sbuf.append(HEX[ch]);
    }
 else {
      sbuf.append('%');
      sbuf.append('u');
      sbuf.append(HEX[(ch >>> 8)]);
      sbuf.append(HEX[(0x00FF & ch)]);
    }
  }
  return sbuf.toString();
}
