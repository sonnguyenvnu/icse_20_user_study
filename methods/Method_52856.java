@Override protected AntlrTokenManager getLexerForSource(final SourceCode sourceCode){
  CharStream charStream=AntlrTokenizer.getCharStreamFromSourceCode(sourceCode);
  return new AntlrTokenManager(new SwiftLexer(charStream),sourceCode.getFileName());
}
