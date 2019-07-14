@Override public void tokenize(SourceCode tokens,Tokens tokenEntries){
  code=tokens.getCode();
  for (lineNumber=0; lineNumber < code.size(); lineNumber++) {
    currentLine=code.get(lineNumber);
    int loc=0;
    while (loc < currentLine.length()) {
      StringBuilder token=new StringBuilder();
      loc=getTokenFromLine(token,loc);
      if (token.length() > 0 && !isIgnorableString(token.toString())) {
        if (downcaseString) {
          token=new StringBuilder(token.toString().toLowerCase(Locale.ROOT));
        }
        tokenEntries.add(new TokenEntry(token.toString(),tokens.getFileName(),lineNumber + 1));
      }
    }
  }
  tokenEntries.add(TokenEntry.getEOF());
}
