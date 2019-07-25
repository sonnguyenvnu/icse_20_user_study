/** 
 * Inserts a word into the trie. 
 */
public void insert(String word){
  if (word != null) {
    add(0,word,word.length());
  }
}
