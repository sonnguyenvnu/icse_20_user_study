static public Writer quote(String string,Writer w) throws IOException {
  if (string == null || string.length() == 0) {
    w.write("\"\"");
    return w;
  }
  char b;
  char c=0;
  String hhhh;
  int i;
  int len=string.length();
  w.write('"');
  for (i=0; i < len; i+=1) {
    b=c;
    c=string.charAt(i);
switch (c) {
case '\\':
case '"':
      w.write('\\');
    w.write(c);
  break;
case '/':
if (b == '<') {
  w.write('\\');
}
w.write(c);
break;
case '\b':
w.write("\\b");
break;
case '\t':
w.write("\\t");
break;
case '\n':
w.write("\\n");
break;
case '\f':
w.write("\\f");
break;
case '\r':
w.write("\\r");
break;
default :
if (c < ' ' || (c >= '\u0080' && c < '\u00a0') || (c >= '\u2000' && c < '\u2100')) {
w.write("\\u");
hhhh=Integer.toHexString(c);
w.write("0000",0,4 - hhhh.length());
w.write(hhhh);
}
 else {
w.write(c);
}
}
}
w.write('"');
return w;
}
