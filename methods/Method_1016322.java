/** 
 * the size of the dictionary
 * @return the number of words in the dictionary
 */
public int size(){
  int size=0;
  for (  Dictionary dict : this.dictionaries.values()) {
    size+=dict.size();
  }
  return size;
}
