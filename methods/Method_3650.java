public static List<Term> segment(String text){
  List<Term> termList=new LinkedList<Term>();
  for (  String sentence : SentencesUtil.toSentenceList(text)) {
    termList.addAll(segSentence(sentence));
  }
  return termList;
}
