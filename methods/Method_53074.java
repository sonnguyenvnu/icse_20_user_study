private void string(String value){
  out.append("\"");
  for (int i=0, length=value.length(); i < length; i++) {
    char c=value.charAt(i);
switch (c) {
case '"':
case '\\':
case '/':
      out.append('\\').append(c);
    break;
case '\t':
  out.append("\\t");
break;
case '\b':
out.append("\\b");
break;
case '\n':
out.append("\\n");
break;
case '\r':
out.append("\\r");
break;
case '\f':
out.append("\\f");
break;
default :
if (c <= 0x1F) {
out.append(String.format("\\u%04x",(int)c));
}
 else {
out.append(c);
}
break;
}
}
out.append("\"");
}
