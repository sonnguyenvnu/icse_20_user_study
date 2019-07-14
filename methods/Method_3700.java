public static String combine(String... termArray){
  StringBuilder sbSentence=new StringBuilder();
  for (  String word : termArray) {
    sbSentence.append(word);
  }
  return sbSentence.toString();
}
