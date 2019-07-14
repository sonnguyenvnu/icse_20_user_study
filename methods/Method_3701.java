public static String combine(Sentence sentence){
  StringBuilder sb=new StringBuilder(sentence.wordList.size() * 3);
  for (  IWord word : sentence.wordList) {
    sb.append(word.getValue());
  }
  return sb.toString();
}
