@Override protected void addToDictionary(List<List<IWord>> sentenceList){
  if (verbose)   System.out.println("??????");
  for (  List<IWord> wordList : sentenceList) {
    for (    IWord word : wordList) {
      if (!word.getLabel().equals(NR.A.toString())) {
        dictionaryMaker.add(word);
      }
    }
  }
  for (  List<IWord> wordList : sentenceList) {
    IWord pre=null;
    for (    IWord word : wordList) {
      if (pre != null) {
        nGramDictionaryMaker.addPair(pre,word);
      }
      pre=word;
    }
  }
}
