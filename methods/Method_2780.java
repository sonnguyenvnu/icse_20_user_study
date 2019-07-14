public Sentence mergeCompoundWords(){
  ListIterator<IWord> listIterator=wordList.listIterator();
  while (listIterator.hasNext()) {
    IWord word=listIterator.next();
    if (word instanceof CompoundWord) {
      listIterator.set(new Word(word.getValue(),word.getLabel()));
    }
  }
  return this;
}
