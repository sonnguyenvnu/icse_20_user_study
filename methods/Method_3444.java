public static String[] toWordArray(List<Word> wordList){
  String[] wordArray=new String[wordList.size()];
  int i=-1;
  for (  Word word : wordList) {
    wordArray[++i]=word.getValue();
  }
  return wordArray;
}
