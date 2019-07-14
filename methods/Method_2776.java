/** 
 * ?????????
 * @return
 */
public List<Word> toSimpleWordList(){
  List<Word> wordList=new LinkedList<Word>();
  for (  IWord word : this.wordList) {
    if (word instanceof CompoundWord) {
      wordList.addAll(((CompoundWord)word).innerList);
    }
 else {
      wordList.add((Word)word);
    }
  }
  return wordList;
}
