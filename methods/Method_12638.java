/** 
 * ??????????
 */
public void disableWords(Collection<String> words){
  if (words != null) {
    for (    String word : words) {
      if (word != null) {
        singleton._MainDict.disableSegment(word.trim().toCharArray());
      }
    }
  }
}
