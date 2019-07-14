private String tokenize(KeyInformation.StoreRetriever information,Object value,String key,JanusGraphPredicate janusgraphPredicate,String tokenizer){
  List<String> terms;
  if (tokenizer != null) {
    terms=customTokenize(tokenizer,(String)value);
  }
 else {
    terms=Text.tokenize((String)value);
  }
  if (terms.isEmpty()) {
    return "";
  }
 else   if (terms.size() == 1) {
    return (key + ":(" + escapeValue(terms.get(0)) + ")");
  }
 else {
    final And<JanusGraphElement> andTerms=new And<>();
    for (    final String term : terms) {
      andTerms.add(new PredicateCondition<>(key,janusgraphPredicate,term));
    }
    return buildQueryFilter(andTerms,information);
  }
}
