@Override public Map<String,AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers(){
  Map<String,AnalysisModule.AnalysisProvider<TokenizerFactory>> extra=new HashMap<>();
  extra.put("ik_smart",IkTokenizerFactory::getIkSmartTokenizerFactory);
  extra.put("ik_max_word",IkTokenizerFactory::getIkTokenizerFactory);
  return extra;
}
