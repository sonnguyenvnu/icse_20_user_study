@Override public void segment(String sentence,String normalized,List<String> wordList){
  LexicalAnalyzer analyzer=getAnalyzer();
  if (analyzer == null)   throw new IllegalStateException("??????LexicalAnalyzerPipe");
  analyzer.segment(sentence,normalized,wordList);
}
