/** 
 * return the key corresponding to the maximum value or null if no entries 
 */
public String maxKey(){
  int index=maxIndex();
  if (index == -1) {
    return null;
  }
  return keys[index];
}
