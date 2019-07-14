@Override protected List<String[]> convertToSequence(Sentence sentence){
  List<Word> wordList=sentence.toSimpleWordList();
  List<String[]> xyList=new ArrayList<String[]>(wordList.size());
  for (  Word word : wordList) {
    xyList.add(new String[]{word.getValue(),word.getLabel()});
  }
  return xyList;
}
