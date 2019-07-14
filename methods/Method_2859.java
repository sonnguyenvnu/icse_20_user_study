public static List<IWord> spilt(List<IWord> wordList){
  ListIterator<IWord> listIterator=wordList.listIterator();
  while (listIterator.hasNext()) {
    IWord word=listIterator.next();
    if (word instanceof CompoundWord) {
      listIterator.remove();
      for (      Word inner : ((CompoundWord)word).innerList) {
        listIterator.add(inner);
      }
    }
  }
  return wordList;
}
