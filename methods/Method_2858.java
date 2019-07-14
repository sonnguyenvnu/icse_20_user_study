/** 
 * ?word???????IWord??
 * @param simpleSentenceList
 * @return
 */
public static List<List<IWord>> convert2CompatibleList(List<List<Word>> simpleSentenceList){
  List<List<IWord>> compatibleList=new LinkedList<List<IWord>>();
  for (  List<Word> wordList : simpleSentenceList) {
    compatibleList.add(new LinkedList<IWord>(wordList));
  }
  return compatibleList;
}
