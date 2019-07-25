private void quote(final String text) throws IOException {
  final int len=text.length();
  for (int i=0; i < len; i++) {
    final char c=text.charAt(i);
switch (c) {
case '<':
      writer.write("&lt;");
    break;
case '>':
  writer.write("&gt;");
break;
case '"':
writer.write("&quot;");
break;
case '&':
writer.write("&amp;");
break;
default :
writer.write(c);
break;
}
}
}
