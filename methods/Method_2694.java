public static boolean makeModel(String corpusLoadPath,String modelSavePath){
  Set<String> posSet=new TreeSet<String>();
  DictionaryMaker dictionaryMaker=new DictionaryMaker();
  for (  CoNLLSentence sentence : CoNLLLoader.loadSentenceList(corpusLoadPath)) {
    for (    CoNLLWord word : sentence.word) {
      addPair(word.NAME,word.HEAD.NAME,word.DEPREL,dictionaryMaker);
      addPair(word.NAME,wrapTag(word.HEAD.POSTAG),word.DEPREL,dictionaryMaker);
      addPair(wrapTag(word.POSTAG),word.HEAD.NAME,word.DEPREL,dictionaryMaker);
      addPair(wrapTag(word.POSTAG),wrapTag(word.HEAD.POSTAG),word.DEPREL,dictionaryMaker);
      posSet.add(word.POSTAG);
    }
  }
  for (  CoNLLSentence sentence : CoNLLLoader.loadSentenceList(corpusLoadPath)) {
    for (    CoNLLWord word : sentence.word) {
      addPair(word.NAME,word.HEAD.NAME,word.DEPREL,dictionaryMaker);
      addPair(word.NAME,wrapTag(word.HEAD.POSTAG),word.DEPREL,dictionaryMaker);
      addPair(wrapTag(word.POSTAG),word.HEAD.NAME,word.DEPREL,dictionaryMaker);
      addPair(wrapTag(word.POSTAG),wrapTag(word.HEAD.POSTAG),word.DEPREL,dictionaryMaker);
      posSet.add(word.POSTAG);
    }
  }
  StringBuilder sb=new StringBuilder();
  for (  String pos : posSet) {
    sb.append("case \"" + pos + "\":\n");
  }
  IOUtil.saveTxt("data/model/dependency/pos-thu.txt",sb.toString());
  return dictionaryMaker.saveTxtTo(modelSavePath);
}
