@Override protected TokenManager getLexerForSource(SourceCode sourceCode){
  final StringBuilder stringBuilder=sourceCode.getCodeBuffer();
  return new JavaTokenManager(new StringReader(stringBuilder.toString()));
}
