/** 
 * ????????????????
 * @param word
 * @return
 */
public boolean endsWith(String word){
  word=reverse(word);
  return trie.commonPrefixSearchWithValue(word).size() > 0;
}
