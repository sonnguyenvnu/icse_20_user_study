protected void check(final JSONLexer lexer,int token){
  if (lexer.token() != token) {
    throw new JSONException("syntax error");
  }
}
