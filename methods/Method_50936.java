private boolean matchEnded(TokenEntry token1,TokenEntry token2){
  return token1.getIdentifier() != token2.getIdentifier() || token1 == TokenEntry.EOF || token2 == TokenEntry.EOF;
}
