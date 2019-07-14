/** 
 * ????
 * @param wordList
 * @return
 */
public String[] partOfSpeechTag(List<String> wordList){
  if (posTagger == null) {
    throw new IllegalStateException("?????????");
  }
  return tag(wordList);
}
