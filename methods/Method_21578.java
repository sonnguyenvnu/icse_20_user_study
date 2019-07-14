@SuppressWarnings({"unchecked","rawtypes"}) public void parseHints(List hints){
  while (lexer.token() == Token.HINT) {
    SQLCommentHint hint=new SQLCommentHint(lexer.stringVal());
    if (lexer.getCommentCount() > 0) {
      hint.addBeforeComment(lexer.readAndResetComments());
    }
    hints.add(hint);
    lexer.nextToken();
  }
}
