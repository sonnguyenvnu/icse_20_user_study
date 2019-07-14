public SQLName userName(){
  SQLName name=this.name();
  if (lexer.token() == Token.LPAREN && name.hashCode64() == FnvHash.Constants.CURRENT_USER) {
    lexer.nextToken();
    accept(Token.RPAREN);
    return name;
  }
  return (SQLName)userNameRest(name);
}
