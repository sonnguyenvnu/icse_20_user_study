private void processToken(final Tokens tokenEntries,final String fileName,final AntlrToken token){
  final TokenEntry tokenEntry=new TokenEntry(token.getImage(),fileName,token.getBeginLine());
  tokenEntries.add(tokenEntry);
}
