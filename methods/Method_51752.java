@Override protected AntlrTokenManager getLexerForSource(SourceCode sourceCode){
  CharStream charStream=AntlrTokenizer.getCharStreamFromSourceCode(sourceCode);
  return new AntlrTokenManager(new GolangLexer(charStream),sourceCode.getFileName());
}
