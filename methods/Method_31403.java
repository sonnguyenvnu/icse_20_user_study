@Override protected boolean shouldDiscard(Token token,boolean nonCommentPartSeen){
  return ("/".equals(token.getText()) && !nonCommentPartSeen) || super.shouldDiscard(token,nonCommentPartSeen);
}
