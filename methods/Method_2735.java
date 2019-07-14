@Override protected void roleTag(List<List<IWord>> sentenceList){
  int i=0;
  for (  List<IWord> wordList : sentenceList) {
    Precompiler.compileWithoutNS(wordList);
    if (verbose) {
      System.out.print(++i + " / " + sentenceList.size() + " ");
      System.out.println("???? " + wordList);
    }
    LinkedList<IWord> wordLinkedList=(LinkedList<IWord>)wordList;
    wordLinkedList.addFirst(new Word(Predefine.TAG_BIGIN,"S"));
    wordLinkedList.addLast(new Word(Predefine.TAG_END,"Z"));
    if (verbose)     System.out.println("???? " + wordList);
    Iterator<IWord> iterator=wordLinkedList.iterator();
    IWord pre=iterator.next();
    while (iterator.hasNext()) {
      IWord current=iterator.next();
      if (current.getLabel().startsWith("ns") && !pre.getLabel().startsWith("ns") && !pre.getValue().equals(Predefine.TAG_BIGIN)) {
        pre.setLabel(NS.A.toString());
      }
      pre=current;
    }
    if (verbose)     System.out.println("???? " + wordList);
    iterator=wordLinkedList.descendingIterator();
    pre=iterator.next();
    while (iterator.hasNext()) {
      IWord current=iterator.next();
      if (current.getLabel().startsWith("ns") && !pre.getLabel().startsWith("ns")) {
        pre.setLabel(NS.B.toString());
      }
      pre=current;
    }
    if (verbose)     System.out.println("???? " + wordList);
    iterator=wordLinkedList.iterator();
    IWord first=iterator.next();
    IWord second=iterator.next();
    while (iterator.hasNext()) {
      IWord third=iterator.next();
      if (first.getLabel().startsWith("ns") && third.getLabel().startsWith("ns") && !second.getLabel().startsWith("ns")) {
        second.setLabel(NS.X.toString());
      }
      first=second;
      second=third;
    }
    if (verbose)     System.out.println("???? " + wordList);
    CorpusUtil.spilt(wordList);
    if (verbose)     System.out.println("???? " + wordList);
    ListIterator<IWord> listIterator=wordLinkedList.listIterator();
    while (listIterator.hasNext()) {
      IWord word=listIterator.next();
      String label=word.getLabel();
      if (label.equals(label.toUpperCase()))       continue;
      if (label.startsWith("ns")) {
        String value=word.getValue();
        int longestSuffixLength=PlaceSuffixDictionary.dictionary.getLongestSuffixLength(value);
        int wordLength=value.length() - longestSuffixLength;
        if (longestSuffixLength == 0 || wordLength == 0) {
          word.setLabel(NS.G.toString());
          continue;
        }
        listIterator.remove();
        if (wordLength > 3) {
          listIterator.add(new Word(value.substring(0,wordLength),NS.G.toString()));
          listIterator.add(new Word(value.substring(wordLength),NS.H.toString()));
          continue;
        }
        for (int l=1, tag=NS.C.ordinal(); l <= wordLength; ++l, ++tag) {
          listIterator.add(new Word(value.substring(l - 1,l),NS.values()[tag].toString()));
        }
        listIterator.add(new Word(value.substring(wordLength),NS.H.toString()));
      }
 else {
        word.setLabel(NS.Z.toString());
      }
    }
    if (verbose)     System.out.println("???? " + wordList);
  }
}
