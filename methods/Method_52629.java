@Override protected TokenEntry processToken(Tokens tokenEntries,GenericToken currentToken,String filename){
  return new TokenEntry(getTokenImage(currentToken),filename,currentToken.getBeginLine());
}
