@Override protected TokenManager getLexerForSource(SourceCode sourceCode){
  StringBuilder stringBuilder=sourceCode.getCodeBuffer();
  return new PLSQLTokenManager(IOUtil.skipBOM(new StringReader(stringBuilder.toString())));
}
