public static CWSInstance create(Sentence sentence,FeatureMap featureMap){
  if (sentence == null || featureMap == null) {
    return null;
  }
  List<Word> wordList=sentence.toSimpleWordList();
  String[] termArray=new String[wordList.size()];
  int i=0;
  for (  Word word : wordList) {
    termArray[i]=word.getValue();
    ++i;
  }
  return new CWSInstance(termArray,featureMap);
}
