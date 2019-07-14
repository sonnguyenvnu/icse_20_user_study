public final void accept(final int token){
  final JSONLexer lexer=this.lexer;
  if (lexer.token() == token) {
    lexer.nextToken();
  }
 else {
    throw new JSONException("syntax error, expect " + JSONToken.name(token) + ", actual " + JSONToken.name(lexer.token()));
  }
}
