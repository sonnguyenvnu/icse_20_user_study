/** 
 * Produce a string in double quotes with backslash sequences in all the right places. A backslash will be inserted within </, producing <\/, allowing JSON text to be delivered in HTML. In JSON text, a string cannot contain a control character or an unescaped quote or backslash.
 * @param string A String
 * @return A String correctly formatted for insertion in a JSON text.
 */
public static String quote(String string){
  if (string == null || string.length() == 0) {
    return "\"\"";
  }
  char b;
  char c=0;
  String hhhh;
  int i;
  int len=string.length();
  StringBuffer sb=new StringBuffer(len + 4);
  sb.append('"');
  for (i=0; i < len; i+=1) {
    b=c;
    c=string.charAt(i);
switch (c) {
case '\\':
case '"':
      sb.append('\\');
    sb.append(c);
  break;
case '/':
if (b == '<') {
  sb.append('\\');
}
sb.append(c);
break;
case '\b':
sb.append("\\b");
break;
case '\t':
sb.append("\\t");
break;
case '\n':
sb.append("\\n");
break;
case '\f':
sb.append("\\f");
break;
case '\r':
sb.append("\\r");
break;
default :
if (c < ' ' || (c >= '\u0080' && c < '\u00a0') || (c >= '\u2000' && c < '\u2100')) {
hhhh="000" + Integer.toHexString(c);
sb.append("\\u" + hhhh.substring(hhhh.length() - 4));
}
 else {
sb.append(c);
}
}
}
sb.append('"');
return sb.toString();
}
