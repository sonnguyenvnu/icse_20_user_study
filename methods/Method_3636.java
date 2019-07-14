@Override public String[] tag(List<String> wordList){
  LexicalAnalyzer analyzer=getAnalyzer();
  if (analyzer == null)   throw new IllegalStateException("??????LexicalAnalyzerPipe");
  return analyzer.tag(wordList);
}
