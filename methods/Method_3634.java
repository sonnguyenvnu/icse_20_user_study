@Override public String[] recognize(String[] wordArray,String[] posArray){
  LexicalAnalyzer analyzer=getAnalyzer();
  if (analyzer == null)   throw new IllegalStateException("??????LexicalAnalyzerPipe");
  return analyzer.recognize(wordArray,posArray);
}
