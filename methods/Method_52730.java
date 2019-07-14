@Override public void tokenize(SourceCode sourceCode,Tokens tokenEntries){
  StringBuilder buffer=sourceCode.getCodeBuffer();
  LanguageVersionHandler languageVersionHandler=LanguageRegistry.getLanguage(JspLanguageModule.NAME).getDefaultVersion().getLanguageVersionHandler();
  try (Reader reader=IOUtil.skipBOM(new StringReader(buffer.toString()))){
    TokenManager tokenMgr=languageVersionHandler.getParser(languageVersionHandler.getDefaultParserOptions()).getTokenManager(sourceCode.getFileName(),reader);
    Token currentToken=(Token)tokenMgr.getNextToken();
    while (currentToken.image.length() > 0) {
      tokenEntries.add(new TokenEntry(String.valueOf(currentToken.kind),sourceCode.getFileName(),currentToken.beginLine));
      currentToken=(Token)tokenMgr.getNextToken();
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  tokenEntries.add(TokenEntry.getEOF());
}
