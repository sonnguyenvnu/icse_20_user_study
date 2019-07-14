static public void scrubCommentsAndStrings(StringBuilder p){
  final int length=p.length();
  final int OUT=0;
  final int IN_BLOCK_COMMENT=1;
  final int IN_EOL_COMMENT=2;
  final int IN_STRING_LITERAL=3;
  final int IN_CHAR_LITERAL=4;
  int blockStart=-1;
  int prevState=OUT;
  int state=OUT;
  for (int i=0; i <= length; i++) {
    char ch=(i < length) ? p.charAt(i) : 0;
    char pch=(i == 0) ? 0 : p.charAt(i - 1);
    if (pch == '\\' && ch == '\\') {
      p.setCharAt(i - 1,' ');
      p.setCharAt(i,' ');
      pch=' ';
      ch=' ';
    }
switch (state) {
case OUT:
switch (ch) {
case '\'':
        state=IN_CHAR_LITERAL;
      break;
case '"':
    state=IN_STRING_LITERAL;
  break;
case '*':
if (pch == '/') state=IN_BLOCK_COMMENT;
break;
case '/':
if (pch == '/') state=IN_EOL_COMMENT;
break;
}
break;
case IN_BLOCK_COMMENT:
if (pch == '*' && ch == '/' && (i - blockStart) > 0) {
state=OUT;
}
break;
case IN_EOL_COMMENT:
if (ch == '\r' || ch == '\n') {
state=OUT;
}
break;
case IN_STRING_LITERAL:
if ((pch != '\\' && ch == '"') || ch == '\r' || ch == '\n') {
state=OUT;
}
break;
case IN_CHAR_LITERAL:
if ((pch != '\\' && ch == '\'') || ch == '\r' || ch == '\n') {
state=OUT;
}
break;
}
if (i == length) {
state=OUT;
}
if (state != prevState) {
if (state != OUT) {
blockStart=i + 1;
}
 else {
int blockEnd=i;
if (prevState == IN_BLOCK_COMMENT && i < length) blockEnd--;
for (int j=blockStart; j < blockEnd; j++) {
char c=p.charAt(j);
if (c != '\n' && c != '\r') p.setCharAt(j,' ');
}
}
}
prevState=state;
}
}
