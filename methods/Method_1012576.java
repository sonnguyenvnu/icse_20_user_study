/** 
 * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. 
 */
public boolean search(String word){
  return match(root,word,0);
}
