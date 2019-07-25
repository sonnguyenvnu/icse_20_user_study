/** 
 * add a word to the generic dictionary
 * @param word
 */
public static void learn(final StringBuilder word){
  if (word == null) {
    return;
  }
  if (word.length() < commonWordsMinLength) {
    return;
  }
  commonWords.inc(word);
  if (!(commonWords.sizeSmaller(commonWordsMaxSize))) {
    commonWords.shrinkToMaxSize(commonWordsMaxSize / 2);
  }
}
