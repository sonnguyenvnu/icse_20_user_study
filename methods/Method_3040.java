/** 
 * ?????ID
 * @param a ??
 * @return id
 */
public static int getWordID(String a){
  return CoreDictionary.trie.exactMatchSearch(a);
}
