/** 
 * check if the library contains the given word
 * @param s the given word
 * @return true if the library contains the word
 */
public boolean contains(final StringBuilder s){
  for (  Dictionary dict : this.dictionaries.values()) {
    if (dict.contains(s)) {
      return true;
    }
  }
  return false;
}
