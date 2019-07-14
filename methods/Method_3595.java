/** 
 * ??????????
 * @param sentenceList
 * @return
 */
private static List<List<String>> convertSentenceListToDocument(List<String> sentenceList){
  List<List<String>> docs=new ArrayList<List<String>>(sentenceList.size());
  for (  String sentence : sentenceList) {
    List<Term> termList=StandardTokenizer.segment(sentence.toCharArray());
    List<String> wordList=new LinkedList<String>();
    for (    Term term : termList) {
      if (CoreStopWordDictionary.shouldInclude(term)) {
        wordList.add(term.word);
      }
    }
    docs.add(wordList);
  }
  return docs;
}
