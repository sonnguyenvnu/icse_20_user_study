@Override public List<String> segment(String sentence){
  LexicalAnalyzer analyzer=getAnalyzer();
  if (analyzer == null)   throw new IllegalStateException("??????LexicalAnalyzerPipe");
  return analyzer.segment(sentence);
}
