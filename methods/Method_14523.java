static protected double wordDistance(Set<String> longWords,Set<String> shortWords){
  if (longWords.size() == 0) {
    return 0.0;
  }
  double common=0;
  for (  String word : shortWords) {
    if (longWords.contains(word)) {
      common++;
    }
  }
  return common / longWords.size();
}
