public static List<List<Word>> convert2SimpleSentenceList(String path){
  List<Document> documentList=CorpusLoader.convert2DocumentList(path);
  List<List<Word>> simpleList=new LinkedList<List<Word>>();
  for (  Document document : documentList) {
    simpleList.addAll(document.getSimpleSentenceList());
  }
  return simpleList;
}
