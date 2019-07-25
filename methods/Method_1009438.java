@Override public boolean test(Token token){
  return allowedTokenTypes.contains(token.getType());
}
