@SuppressWarnings("unchecked") protected void match(Indexes indexes,String indexName,String pattern,BiFunction<Character,Map<String,Collection>,Map<String,Collection>> matchWithCharFunction,BiFunction<String,Map<String,Collection>,Map<String,Collection>> matchWithStringFunction,Set<Container.Entry> matchingEntries){
  int patternLength=pattern.length();
  if (patternLength > 0) {
    String key=String.valueOf(indexes.hashCode()) + "***" + indexName + "***" + pattern;
    Map<String,Collection> matchedEntries=cache.get(key);
    if (matchedEntries == null) {
      Map<String,Collection> index=indexes.getIndex(indexName);
      if (index != null) {
        if (patternLength == 1) {
          matchedEntries=matchWithCharFunction.apply(pattern.charAt(0),index);
        }
 else {
          String lastKey=key.substring(0,key.length() - 1);
          Map<String,Collection> lastMatchedTypes=cache.get(lastKey);
          if (lastMatchedTypes != null) {
            matchedEntries=matchWithStringFunction.apply(pattern,lastMatchedTypes);
          }
 else {
            matchedEntries=matchWithStringFunction.apply(pattern,index);
          }
        }
      }
      cache.put(key,matchedEntries);
    }
    if (matchedEntries != null) {
      for (      Collection<Container.Entry> entries : matchedEntries.values()) {
        matchingEntries.addAll(entries);
      }
    }
  }
}
