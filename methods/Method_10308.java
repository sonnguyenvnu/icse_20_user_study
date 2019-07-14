static byte[] escape(String string){
  if (string == null) {
    return JSON_NULL;
  }
  StringBuilder sb=new StringBuilder(128);
  sb.append('"');
  int length=string.length(), pos=-1;
  while (++pos < length) {
    char ch=string.charAt(pos);
switch (ch) {
case '"':
      sb.append("\\\"");
    break;
case '\\':
  sb.append("\\\\");
break;
case '\b':
sb.append("\\b");
break;
case '\f':
sb.append("\\f");
break;
case '\n':
sb.append("\\n");
break;
case '\r':
sb.append("\\r");
break;
case '\t':
sb.append("\\t");
break;
default :
if ((ch <= '\u001F') || (ch >= '\u007F' && ch <= '\u009F') || (ch >= '\u2000' && ch <= '\u20FF')) {
String intString=Integer.toHexString(ch);
sb.append("\\u");
int intLength=4 - intString.length();
for (int zero=0; zero < intLength; zero++) {
sb.append('0');
}
sb.append(intString.toUpperCase(Locale.US));
}
 else {
sb.append(ch);
}
break;
}
}
sb.append('"');
return sb.toString().getBytes();
}
