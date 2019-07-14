public Map<String,Object> contains(String key,List<String> terms){
  return boolMust(terms.stream().map(term -> term(key,term)).collect(Collectors.toList()));
}
