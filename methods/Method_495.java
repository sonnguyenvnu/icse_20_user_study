public final void nextToken(int expect){
  sp=0;
  for (; ; ) {
switch (expect) {
case JSONToken.LBRACE:
      if (ch == '{') {
        token=JSONToken.LBRACE;
        next();
        return;
      }
    if (ch == '[') {
      token=JSONToken.LBRACKET;
      next();
      return;
    }
  break;
case JSONToken.COMMA:
if (ch == ',') {
  token=JSONToken.COMMA;
  next();
  return;
}
if (ch == '}') {
token=JSONToken.RBRACE;
next();
return;
}
if (ch == ']') {
token=JSONToken.RBRACKET;
next();
return;
}
if (ch == EOI) {
token=JSONToken.EOF;
return;
}
if (ch == 'n') {
scanNullOrNew(false);
return;
}
break;
case JSONToken.LITERAL_INT:
if (ch >= '0' && ch <= '9') {
pos=bp;
scanNumber();
return;
}
if (ch == '"') {
pos=bp;
scanString();
return;
}
if (ch == '[') {
token=JSONToken.LBRACKET;
next();
return;
}
if (ch == '{') {
token=JSONToken.LBRACE;
next();
return;
}
break;
case JSONToken.LITERAL_STRING:
if (ch == '"') {
pos=bp;
scanString();
return;
}
if (ch >= '0' && ch <= '9') {
pos=bp;
scanNumber();
return;
}
if (ch == '[') {
token=JSONToken.LBRACKET;
next();
return;
}
if (ch == '{') {
token=JSONToken.LBRACE;
next();
return;
}
break;
case JSONToken.LBRACKET:
if (ch == '[') {
token=JSONToken.LBRACKET;
next();
return;
}
if (ch == '{') {
token=JSONToken.LBRACE;
next();
return;
}
break;
case JSONToken.RBRACKET:
if (ch == ']') {
token=JSONToken.RBRACKET;
next();
return;
}
case JSONToken.EOF:
if (ch == EOI) {
token=JSONToken.EOF;
return;
}
break;
case JSONToken.IDENTIFIER:
nextIdent();
return;
default :
break;
}
if (ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t' || ch == '\f' || ch == '\b') {
next();
continue;
}
nextToken();
break;
}
}
