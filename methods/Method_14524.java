static protected Set<String> breakWords(String s){
  String[] words=s.toLowerCase().split("\\s+");
  Set<String> set=new HashSet<String>(words.length);
  for (  String word : words) {
    if (!s_stopWords.contains(word)) {
      set.add(word);
    }
  }
  return set;
}
