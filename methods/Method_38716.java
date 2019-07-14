/** 
 * Write a quoted and escaped value to the output.
 */
public void writeString(final String value){
  popName();
  write(StringPool.QUOTE);
  int len=value.length();
  for (int i=0; i < len; i++) {
    char c=value.charAt(i);
switch (c) {
case '"':
      write("\\\"");
    break;
case '\\':
  write("\\\\");
break;
case '/':
if (strictStringEncoding) {
write("\\/");
}
 else {
write(c);
}
break;
case '\b':
write("\\b");
break;
case '\f':
write("\\f");
break;
case '\n':
write("\\n");
break;
case '\r':
write("\\r");
break;
case '\t':
write("\\t");
break;
default :
if (Character.isISOControl(c)) {
unicode(c);
}
 else {
write(c);
}
}
}
write(StringPool.QUOTE);
}
