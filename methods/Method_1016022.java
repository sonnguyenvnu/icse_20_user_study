/** 
 * Encode a string to the "x-www-form-urlencoded" form, enhanced with the UTF-8-in-URL proposal. This is what happens: <ul> <li>The ASCII characters 'a' through 'z', 'A' through 'Z', and '0' through '9' remain the same. <li>The unreserved characters & : - _ . ! ~ * ' ( ) ; , = remain the same. see RFC 1738 2.2  and  RFC 3986 2.2 <li>All other ASCII characters are converted into the 3-character string "%xy", where xy is the two-digit hexadecimal representation of the character code <li>All non-ASCII characters are encoded in two steps: first to a sequence of 2 or 3 bytes, using the UTF-8 algorithm; secondly each of these bytes is encoded as "%xx". </ul>
 * @param s The string to be encoded
 * @return The encoded string
 */
public static StringBuilder escape(final String s){
  final int len=s.length();
  final StringBuilder sbuf=new StringBuilder(len + 10);
  for (int i=0; i < len; i++) {
    final int ch=s.charAt(i);
    if (ch == ' ') {
      sbuf.append("%20");
    }
 else     if (ch == '%') {
      if (i < len - 2 && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9' && s.charAt(i + 2) >= '0' && s.charAt(i + 2) <= '9') {
        sbuf.append((char)ch);
      }
 else {
        sbuf.append("%25");
      }
    }
 else     if (ch == '&') {
      if (i < len - 6 && "amp;".equals(s.substring(i + 1,i + 5).toLowerCase())) {
        sbuf.append((char)ch);
      }
 else {
        sbuf.append("%26");
      }
    }
 else     if (ch == '#') {
      sbuf.append((char)ch);
    }
 else     if (ch == '!' || ch == ':' || ch == '-' || ch == '_' || ch == '.' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')' || ch == '{' || ch == '}' || ch == ';' || ch == ',' || ch == '=') {
      sbuf.append((char)ch);
    }
 else     if ('0' <= ch && ch <= '9') {
      sbuf.append((char)ch);
    }
 else     if (ch == '/') {
      sbuf.append((char)ch);
    }
 else     if ('A' <= ch && ch <= 'Z') {
      sbuf.append((char)ch);
    }
 else     if ('a' <= ch && ch <= 'z') {
      sbuf.append((char)ch);
    }
 else     if (ch <= 0x007f) {
      sbuf.append(hex[ch]);
    }
 else     if (ch <= 0x07FF) {
      sbuf.append(hex[0xc0 | (ch >> 6)]);
      sbuf.append(hex[0x80 | (ch & 0x3F)]);
    }
 else {
      sbuf.append(hex[0xe0 | (ch >> 12)]);
      sbuf.append(hex[0x80 | ((ch >> 6) & 0x3F)]);
      sbuf.append(hex[0x80 | (ch & 0x3F)]);
    }
  }
  return sbuf;
}
