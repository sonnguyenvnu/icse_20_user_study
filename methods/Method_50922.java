protected TokenEntry processToken(Tokens tokenEntries,GenericToken currentToken,String filename){
  return new TokenEntry(currentToken.getImage(),filename,currentToken.getBeginLine());
}
