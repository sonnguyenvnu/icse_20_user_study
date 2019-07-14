@Override public Sentence analyze(final String sentence){
  if (sentence.isEmpty()) {
    return new Sentence(Collections.<IWord>emptyList());
  }
  final String normalized=CharTable.convert(sentence);
  List<String> wordList=new LinkedList<String>();
  List<CoreDictionary.Attribute> attributeList=segmentWithAttribute(sentence,normalized,wordList);
  String[] wordArray=new String[wordList.size()];
  int offset=0;
  int id=0;
  for (  String word : wordList) {
    wordArray[id]=normalized.substring(offset,offset + word.length());
    ++id;
    offset+=word.length();
  }
  List<IWord> termList=new ArrayList<IWord>(wordList.size());
  if (posTagger != null) {
    String[] posArray=tag(wordArray);
    if (neRecognizer != null) {
      String[] nerArray=neRecognizer.recognize(wordArray,posArray);
      overwriteTag(attributeList,posArray);
      wordList.toArray(wordArray);
      List<Word> result=new LinkedList<Word>();
      result.add(new Word(wordArray[0],posArray[0]));
      String prePos=posArray[0];
      NERTagSet tagSet=getNERTagSet();
      for (int i=1; i < nerArray.length; i++) {
        if (nerArray[i].charAt(0) == tagSet.B_TAG_CHAR || nerArray[i].charAt(0) == tagSet.S_TAG_CHAR || nerArray[i].charAt(0) == tagSet.O_TAG_CHAR) {
          termList.add(result.size() > 1 ? new CompoundWord(result,prePos) : result.get(0));
          result=new ArrayList<Word>();
        }
        result.add(new Word(wordArray[i],posArray[i]));
        if (nerArray[i].charAt(0) == tagSet.O_TAG_CHAR || nerArray[i].charAt(0) == tagSet.S_TAG_CHAR) {
          prePos=posArray[i];
        }
 else {
          prePos=NERTagSet.posOf(nerArray[i]);
        }
      }
      if (result.size() != 0) {
        termList.add(result.size() > 1 ? new CompoundWord(result,prePos) : result.get(0));
      }
    }
 else {
      overwriteTag(attributeList,posArray);
      wordList.toArray(wordArray);
      for (int i=0; i < wordArray.length; i++) {
        termList.add(new Word(wordArray[i],posArray[i]));
      }
    }
  }
 else {
    wordList.toArray(wordArray);
    for (    String word : wordArray) {
      termList.add(new Word(word,null));
    }
  }
  return new Sentence(termList);
}
