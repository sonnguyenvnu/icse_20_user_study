public void build(final Map<String,TokenizerFactory> tokenizers,final Map<String,CharFilterFactory> charFilters,final Map<String,TokenFilterFactory> tokenFilters){
  String tokenizerName=analyzerSettings.get("tokenizer");
  if (tokenizerName == null) {
    throw new IllegalArgumentException("Custom Analyzer [" + name() + "] must be configured with a tokenizer");
  }
  TokenizerFactory tokenizer=tokenizers.get(tokenizerName);
  if (tokenizer == null) {
    throw new IllegalArgumentException("Custom Analyzer [" + name() + "] failed to find tokenizer under name [" + tokenizerName + "]");
  }
  List<String> charFilterNames=analyzerSettings.getAsList("char_filter");
  List<CharFilterFactory> charFiltersList=new ArrayList<>(charFilterNames.size());
  for (  String charFilterName : charFilterNames) {
    CharFilterFactory charFilter=charFilters.get(charFilterName);
    if (charFilter == null) {
      throw new IllegalArgumentException("Custom Analyzer [" + name() + "] failed to find char_filter under name [" + charFilterName + "]");
    }
    charFiltersList.add(charFilter);
  }
  int positionIncrementGap=TextFieldMapper.Defaults.POSITION_INCREMENT_GAP;
  positionIncrementGap=analyzerSettings.getAsInt("position_increment_gap",positionIncrementGap);
  int offsetGap=analyzerSettings.getAsInt("offset_gap",-1);
  List<String> tokenFilterNames=analyzerSettings.getAsList("filter");
  List<TokenFilterFactory> tokenFilterList=new ArrayList<>(tokenFilterNames.size());
  for (  String tokenFilterName : tokenFilterNames) {
    TokenFilterFactory tokenFilter=tokenFilters.get(tokenFilterName);
    if (tokenFilter == null) {
      throw new IllegalArgumentException("Custom Analyzer [" + name() + "] failed to find filter under name [" + tokenFilterName + "]");
    }
    tokenFilter=checkAndApplySynonymFilter(tokenFilter,tokenizerName,tokenizer,tokenFilterList,charFiltersList,this.environment);
    tokenFilterList.add(tokenFilter);
  }
  this.customAnalyzer=new CustomAnalyzer(tokenizerName,tokenizer,charFiltersList.toArray(new CharFilterFactory[charFiltersList.size()]),tokenFilterList.toArray(new TokenFilterFactory[tokenFilterList.size()]),positionIncrementGap,offsetGap);
}
