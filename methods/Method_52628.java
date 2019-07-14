@Override protected TokenManager getLexerForSource(SourceCode sourceCode){
  StringBuilder buffer=sourceCode.getCodeBuffer();
  return new Ecmascript5TokenManager(IOUtil.skipBOM(new StringReader(buffer.toString())));
}
