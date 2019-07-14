@Override public List<IWord> flow(List<IWord> input){
  ListIterator<IWord> listIterator=input.listIterator();
  while (listIterator.hasNext()) {
    IWord wordOrSentence=listIterator.next();
    if (wordOrSentence.getLabel() != null)     continue;
    listIterator.remove();
    String sentence=wordOrSentence.getValue();
    for (    IWord word : analyzer.analyze(sentence)) {
      listIterator.add(word);
    }
  }
  return input;
}
