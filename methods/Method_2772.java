/** 
 * ???????label?????????????????
 * @param label
 * @return
 */
public List<IWord> findWordsByLabel(String label){
  List<IWord> wordList=new LinkedList<IWord>();
  for (  IWord word : this) {
    if (label.equals(word.getLabel())) {
      wordList.add(word);
    }
  }
  return wordList;
}
