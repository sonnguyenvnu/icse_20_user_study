@Override protected List<String[]> convertToSequence(Sentence sentence){
  List<String[]> collector=Utility.convertSentenceToNER(sentence,tagSet);
  for (  String[] pair : collector) {
    pair[1]=pair[2];
  }
  return collector;
}
