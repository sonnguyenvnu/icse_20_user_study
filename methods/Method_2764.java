/** 
 * ?????????
 * @param spilt ??????????????????
 * @return
 */
public List<List<Word>> getSimpleSentenceList(boolean spilt){
  List<List<Word>> simpleList=new LinkedList<List<Word>>();
  for (  Sentence sentence : sentenceList) {
    List<Word> wordList=new LinkedList<Word>();
    for (    IWord word : sentence.wordList) {
      if (word instanceof CompoundWord) {
        if (spilt) {
          for (          Word inner : ((CompoundWord)word).innerList) {
            wordList.add(inner);
          }
        }
 else {
          wordList.add(((CompoundWord)word).toWord());
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
