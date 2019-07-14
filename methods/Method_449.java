public Object parseKey(){
  if (lexer.token() == JSONToken.IDENTIFIER) {
    String value=lexer.stringVal();
    lexer.nextToken(JSONToken.COMMA);
    return value;
  }
  return parse(null);
}
