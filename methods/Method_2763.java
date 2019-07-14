/** 
 * ???????????????????????
 * @return
 */
public List<List<Word>> getSimpleSentenceList(){
  List<List<Word>> simpleList=new LinkedList<List<Word>>();
  for (  Sentence sentence : sentenceList) {
    List<Word> wordList=new LinkedList<Word>();
    for (    IWord word : sentence.wordList) {
      if (word instanceof CompoundWord) {
        for (        Word inner : ((CompoundWord)word).innerList) {
          wordList.add(inner);
        }
      }
 else {
        wordList.add((Word)word);
      }
    }
    simpleList.add(wordList);
  }
  return simpleList;
}
