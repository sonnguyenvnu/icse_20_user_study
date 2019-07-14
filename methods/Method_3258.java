public String[] tag(List<String> wordList){
  String[] words=new String[wordList.size()];
  wordList.toArray(words);
  return tag(words);
}
