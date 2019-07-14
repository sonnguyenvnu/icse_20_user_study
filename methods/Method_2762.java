/** 
 * ??????
 * @return
 */
public List<IWord> getWordList(){
  List<IWord> wordList=new LinkedList<IWord>();
  for (  Sentence sentence : sentenceList) {
    wordList.addAll(sentence.wordList);
  }
  return wordList;
}
