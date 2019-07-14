private int nextCleanInternal() throws JSONException {
  while (pos < in.length()) {
    int c=in.charAt(pos++);
switch (c) {
case '\t':
case ' ':
case '\n':
case '\r':
      continue;
case '/':
    if (pos == in.length()) {
      return c;
    }
  char peek=in.charAt(pos);
switch (peek) {
case '*':
  pos++;
int commentEnd=in.indexOf("*/",pos);
if (commentEnd == -1) {
throw syntaxError("Unterminated comment");
}
pos=commentEnd + 2;
continue;
case '/':
pos++;
skipToEndOfLine();
continue;
default :
return c;
}
case '#':
skipToEndOfLine();
continue;
default :
return c;
}
}
return -1;
}
