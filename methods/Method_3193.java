@Override public List<String> extractPhrase(String text,int size){
  List<String> phraseList=new LinkedList<String>();
  Occurrence occurrence=new Occurrence();
  Filter[] filterChain=new Filter[]{CoreStopWordDictionary.FILTER,new Filter(){
    @Override public boolean shouldInclude(    Term term){
      if (term.nature == t || term.nature == nx)       return false;
      return true;
    }
  }
};
  for (  List<Term> sentence : NotionalTokenizer.seg2sentence(text,filterChain)) {
    if (HanLP.Config.DEBUG) {
      System.out.println(sentence);
    }
    occurrence.addAll(sentence);
  }
  occurrence.compute();
  if (HanLP.Config.DEBUG) {
    System.out.println(occurrence);
    for (    PairFrequency phrase : occurrence.getPhraseByMi()) {
      System.out.print(phrase.getKey().replace(Occurrence.RIGHT,'?') + "\tmi=" + phrase.mi + " , ");
    }
    System.out.println();
    for (    PairFrequency phrase : occurrence.getPhraseByLe()) {
      System.out.print(phrase.getKey().replace(Occurrence.RIGHT,'?') + "\tle=" + phrase.le + " , ");
    }
    System.out.println();
    for (    PairFrequency phrase : occurrence.getPhraseByRe()) {
      System.out.print(phrase.getKey().replace(Occurrence.RIGHT,'?') + "\tre=" + phrase.re + " , ");
    }
    System.out.println();
    for (    PairFrequency phrase : occurrence.getPhraseByScore()) {
      System.out.print(phrase.getKey().replace(Occurrence.RIGHT,'?') + "\tscore=" + phrase.score + " , ");
    }
    System.out.println();
  }
  for (  PairFrequency phrase : occurrence.getPhraseByScore()) {
    if (phraseList.size() == size)     break;
    phraseList.add(phrase.first + phrase.second);
  }
  return phraseList;
}
