@Override public List<IWord> flow(List<IWord> input){
  ListIterator<IWord> listIterator=input.listIterator();
  while (listIterator.hasNext()) {
    IWord wordOrSentence=listIterator.next();
    if (wordOrSentence.getLabel() != null)     continue;
    listIterator.remove();
    String sentence=wordOrSentence.getValue();
    Matcher matcher=pattern.matcher(sentence);
    int begin=0;
    int end;
    while (matcher.find()) {
      end=matcher.start();
      listIterator.add(new Word(sentence.substring(begin,end),null));
      listIterator.add(new Word(matcher.group(),label));
      begin=matcher.end();
    }
    if (begin < sentence.length())     listIterator.add(new Word(sentence.substring(begin),null));
  }
  return input;
}
