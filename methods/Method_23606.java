/** 
 * The key for a max value; null if empty or everything is NaN (no max). 
 */
public String maxKey(){
  int index=maxIndex();
  if (index == -1) {
    return null;
  }
  return keys[index];
}
