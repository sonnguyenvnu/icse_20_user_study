public static boolean isValid(String str){
  if (str == null || str.length() == 0) {
    return false;
  }
  JSONScanner lexer=new JSONScanner(str);
  try {
    lexer.nextToken();
    final int token=lexer.token();
switch (token) {
case JSONToken.LBRACE:
      if (lexer.getCurrent() == JSONLexer.EOI) {
        return false;
      }
    lexer.skipObject(true);
  break;
case JSONToken.LBRACKET:
lexer.skipArray(true);
break;
case JSONToken.LITERAL_INT:
case JSONToken.LITERAL_STRING:
case JSONToken.LITERAL_FLOAT:
case JSONToken.LITERAL_ISO8601_DATE:
case JSONToken.NULL:
case JSONToken.TRUE:
case JSONToken.FALSE:
lexer.nextToken();
break;
default :
return false;
}
return lexer.token() == JSONToken.EOF;
}
 catch (Exception ex) {
return false;
}
 finally {
lexer.close();
}
}
