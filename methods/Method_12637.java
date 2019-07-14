/** 
 * ???????
 * @param words Collection<String>????
 */
public void addWords(Collection<String> words){
  if (words != null) {
    for (    String word : words) {
      if (word != null) {
        singleton._MainDict.fillSegment(word.trim().toCharArray());
      }
    }
  }
}
