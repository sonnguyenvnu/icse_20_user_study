/** 
 * ???????????
 * @return
 */
public String[] toWordArray(){
  List<Word> wordList=toSimpleWordList();
  String[] wordArray=new String[wordList.size()];
  Iterator<Word> iterator=wordList.iterator();
  for (int i=0; i < wordArray.length; i++) {
    wordArray[i]=iterator.next().value;
  }
  return wordArray;
}
