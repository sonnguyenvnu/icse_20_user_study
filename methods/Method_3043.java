/** 
 * ?????ID
 * @param a ??
 * @return ID,?????,???-1
 */
public static int getWordID(String a){
  return CoreDictionary.trie.exactMatchSearch(a);
}
