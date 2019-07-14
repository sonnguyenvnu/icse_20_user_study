/** 
 * Returns position of a word in the vocabulary; if the word is not found, returns -1
 * @param word
 * @return
 */
int searchVocab(String word){
  if (word == null)   return -1;
  Integer pos=vocabIndexMap.get(word);
  return pos == null ? -1 : pos.intValue();
}
