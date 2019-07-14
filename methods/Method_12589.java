@Override public Map<String,AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers(){
  Map<String,AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra=new HashMap<>();
  extra.put("ik_smart",IkAnalyzerProvider::getIkSmartAnalyzerProvider);
  extra.put("ik_max_word",IkAnalyzerProvider::getIkAnalyzerProvider);
  return extra;
}
