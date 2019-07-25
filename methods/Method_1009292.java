private boolean execute(@NotNull QuotableKeywordList match,@NotNull ResolveState state){
  List<QuotableKeywordPair> keywordPairList=match.quotableKeywordPairList();
  boolean keepProcessing=true;
  for (  QuotableKeywordPair keywordPair : keywordPairList) {
    keepProcessing=execute(keywordPair,state);
    if (!keepProcessing) {
      break;
    }
  }
  return keepProcessing;
}
