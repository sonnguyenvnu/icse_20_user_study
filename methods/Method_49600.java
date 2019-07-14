private LuceneCustomAnalyzer delegatingAnalyzerFor(String store,KeyInformation.IndexRetriever information2){
  if (!delegatingAnalyzers.containsKey(store)) {
    delegatingAnalyzers.put(store,new LuceneCustomAnalyzer(store,information2,Analyzer.PER_FIELD_REUSE_STRATEGY));
  }
  return delegatingAnalyzers.get(store);
}
