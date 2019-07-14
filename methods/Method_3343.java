@Override protected List<String[]> convertToSequence(Sentence sentence){
  List<String[]> charList=new LinkedList<String[]>();
  for (  Word w : sentence.toSimpleWordList()) {
    String word=CharTable.convert(w.value);
    if (word.length() == 1) {
      charList.add(new String[]{word,"S"});
    }
 else {
      charList.add(new String[]{word.substring(0,1),"B"});
      for (int i=1; i < word.length() - 1; ++i) {
        charList.add(new String[]{word.substring(i,i + 1),"M"});
      }
      charList.add(new String[]{word.substring(word.length() - 1),"E"});
    }
  }
  return charList;
}
