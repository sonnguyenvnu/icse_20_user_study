/** 
 * word pos
 * @return
 */
public String[][] toWordTagArray(){
  List<Word> wordList=toSimpleWordList();
  String[][] pair=new String[2][wordList.size()];
  Iterator<Word> iterator=wordList.iterator();
  for (int i=0; i < pair[0].length; i++) {
    Word word=iterator.next();
    pair[0][i]=word.value;
    pair[1][i]=word.label;
  }
  return pair;
}
