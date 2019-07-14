/** 
 * ??
 * @param corpus ?????
 */
public void train(String corpus){
  CorpusLoader.walk(corpus,new CorpusLoader.Handler(){
    @Override public void handle(    Document document){
      List<List<Word>> simpleSentenceList=document.getSimpleSentenceList();
      List<List<IWord>> compatibleList=new LinkedList<List<IWord>>();
      for (      List<Word> wordList : simpleSentenceList) {
        compatibleList.add(new LinkedList<IWord>(wordList));
      }
      CommonDictionaryMaker.this.compute(compatibleList);
    }
  }
);
}
