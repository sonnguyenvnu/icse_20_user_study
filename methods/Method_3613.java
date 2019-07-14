/** 
 * ???????????
 * @param sentence
 * @param normalized
 * @return
 */
public List<String> segment(final String sentence,final String normalized){
  final List<String> wordList=new LinkedList<String>();
  segment(sentence,normalized,wordList);
  return wordList;
}
