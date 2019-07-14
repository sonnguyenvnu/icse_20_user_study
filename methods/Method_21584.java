public SQLName nameRest(SQLName name){
  if (lexer.token() == Token.VARIANT && "@".equals(lexer.stringVal())) {
    lexer.nextToken();
    MySqlUserName userName=new MySqlUserName();
    userName.setUserName(((SQLIdentifierExpr)name).getName());
    if (lexer.token() == Token.LITERAL_CHARS) {
      userName.setHost("'" + lexer.stringVal() + "'");
    }
 else {
      userName.setHost(lexer.stringVal());
    }
    lexer.nextToken();
    if (lexer.token() == Token.IDENTIFIED) {
      lexer.nextToken();
      accept(Token.BY);
      userName.setIdentifiedBy(lexer.stringVal());
      lexer.nextToken();
    }
    return userName;
  }
  return super.nameRest(name);
}
