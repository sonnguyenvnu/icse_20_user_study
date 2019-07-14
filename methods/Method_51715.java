@Override protected TokenManager getLexerForSource(SourceCode sourceCode){
  try {
    StringBuilder buffer=sourceCode.getCodeBuffer();
    return new CppTokenManager(IOUtil.skipBOM(new StringReader(maybeSkipBlocks(buffer.toString()))));
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
