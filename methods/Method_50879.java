static CharStream getCharStreamFromSourceCode(final SourceCode sourceCode){
  StringBuilder buffer=sourceCode.getCodeBuffer();
  return CharStreams.fromString(buffer.toString());
}
