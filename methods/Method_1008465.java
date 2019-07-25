public void build(final TokenizerFactory keywordTokenizerFactory,final Map<String,CharFilterFactory> charFilters,final Map<String,TokenFilterFactory> tokenFilters){
  String tokenizerName=analyzerSettings.get("tokenizer");
  if (tokenizerName != null) {
    throw new IllegalArgumentException("Custom normalizer [" + name() + "] cannot configure a tokenizer");
  }
  List<String> charFilterNames=analyzerSettings.getAsList("char_filter");
  List<CharFilterFactory> charFiltersList=new ArrayList<>(charFilterNames.size());
  for (  String charFilterName : charFilterNames) {
    CharFilterFactory charFilter=charFilters.get(charFilterName);
    if (charFilter == null) {
      throw new IllegalArgumentException("Custom normalizer [" + name() + "] failed to find char_filter under name [" + charFilterName + "]");
    }
    if (charFilter instanceof MultiTermAwareComponent == false) {
      throw new IllegalArgumentException("Custom normalizer [" + name() + "] may not use char filter [" + charFilterName + "]");
    }
    charFilter=(CharFilterFactory)((MultiTermAwareComponent)charFilter).getMultiTermComponent();
    charFiltersList.add(charFilter);
  }
  List<String> tokenFilterNames=analyzerSettings.getAsList("filter");
  List<TokenFilterFactory> tokenFilterList=new ArrayList<>(tokenFilterNames.size());
  for (  String tokenFilterName : tokenFilterNames) {
    TokenFilterFactory tokenFilter=tokenFilters.get(tokenFilterName);
    if (tokenFilter == null) {
      throw new IllegalArgumentException("Custom Analyzer [" + name() + "] failed to find filter under name [" + tokenFilterName + "]");
    }
    if (tokenFilter instanceof MultiTermAwareComponent == false) {
      throw new IllegalArgumentException("Custom normalizer [" + name() + "] may not use filter [" + tokenFilterName + "]");
    }
    tokenFilter=(TokenFilterFactory)((MultiTermAwareComponent)tokenFilter).getMultiTermComponent();
    tokenFilterList.add(tokenFilter);
  }
  this.customAnalyzer=new CustomAnalyzer("keyword",keywordTokenizerFactory,charFiltersList.toArray(new CharFilterFactory[charFiltersList.size()]),tokenFilterList.toArray(new TokenFilterFactory[tokenFilterList.size()]));
}
