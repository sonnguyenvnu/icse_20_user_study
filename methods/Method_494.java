public final void nextToken(){
  sp=0;
  for (; ; ) {
    pos=bp;
    if (ch == '/') {
      skipComment();
      continue;
    }
    if (ch == '"') {
      scanString();
      return;
    }
    if (ch == ',') {
      next();
      token=COMMA;
      return;
    }
    if (ch >= '0' && ch <= '9') {
      scanNumber();
      return;
    }
    if (ch == '-') {
      scanNumber();
      return;
    }
switch (ch) {
case '\'':
      if (!isEnabled(Feature.AllowSingleQuotes)) {
        throw new JSONException("Feature.AllowSingleQuotes is false");
      }
    scanStringSingleQuote();
  return;
case ' ':
case '\t':
case '\b':
case '\f':
case '\n':
case '\r':
next();
break;
case 't':
scanTrue();
return;
case 'f':
scanFalse();
return;
case 'n':
scanNullOrNew();
return;
case 'T':
case 'N':
case 'S':
case 'u':
scanIdent();
return;
case '(':
next();
token=LPAREN;
return;
case ')':
next();
token=RPAREN;
return;
case '[':
next();
token=LBRACKET;
return;
case ']':
next();
token=RBRACKET;
return;
case '{':
next();
token=LBRACE;
return;
case '}':
next();
token=RBRACE;
return;
case ':':
next();
token=COLON;
return;
case ';':
next();
token=SEMI;
return;
case '.':
next();
token=DOT;
return;
case '+':
next();
scanNumber();
return;
case 'x':
scanHex();
return;
default :
if (isEOF()) {
if (token == EOF) {
throw new JSONException("EOF error");
}
token=EOF;
eofPos=pos=bp;
}
 else {
if (ch <= 31 || ch == 127) {
next();
break;
}
lexError("illegal.char",String.valueOf((int)ch));
next();
}
return;
}
}
}
