@Override protected TokenManager getLexerForSource(SourceCode sourceCode){
  StringBuilder buffer=sourceCode.getCodeBuffer();
  return new ObjectiveCTokenManager(IOUtil.skipBOM(new StringReader(buffer.toString())));
}
