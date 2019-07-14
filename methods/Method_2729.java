@Override protected void roleTag(List<List<IWord>> sentenceList){
  logger.info("????");
  int i=0;
  for (  List<IWord> wordList : sentenceList) {
    logger.info(++i + " / " + sentenceList.size());
    for (    IWord word : wordList) {
      Precompiler.compile(word);
    }
    LinkedList<IWord> wordLinkedList=(LinkedList<IWord>)wordList;
    wordLinkedList.addFirst(new Word(Predefine.TAG_BIGIN,Nature.begin.toString()));
    wordLinkedList.addLast(new Word(Predefine.TAG_END,Nature.end.toString()));
  }
}
