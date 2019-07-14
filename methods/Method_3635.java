@Override public String[] tag(String... words){
  LexicalAnalyzer analyzer=getAnalyzer();
  if (analyzer == null)   throw new IllegalStateException("??????LexicalAnalyzerPipe");
  return analyzer.tag(words);
}
