/** 
 * ??
 * @param wordList
 * @return
 */
@Override public String[] tag(List<String> wordList){
  String[] termArray=new String[wordList.size()];
  wordList.toArray(termArray);
  return tag(termArray);
}
