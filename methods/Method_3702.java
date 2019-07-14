public static String combine(List<Word> wordList){
  StringBuilder sb=new StringBuilder(wordList.size() * 3);
  for (  IWord word : wordList) {
    sb.append(word.getValue());
  }
  return sb.toString();
}
