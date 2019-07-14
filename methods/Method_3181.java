/** 
 * ?????????????????????????????
 * @param document ??
 * @return ????
 */
protected List<String> preprocess(String document){
  List<Term> termList=segment.seg(document);
  ListIterator<Term> listIterator=termList.listIterator();
  while (listIterator.hasNext()) {
    Term term=listIterator.next();
    if (CoreStopWordDictionary.contains(term.word) || term.nature.startsWith("w")) {
      listIterator.remove();
    }
  }
  List<String> wordList=new ArrayList<String>(termList.size());
  for (  Term term : termList) {
    wordList.add(term.word);
  }
  return wordList;
}
