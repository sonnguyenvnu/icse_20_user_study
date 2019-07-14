/** 
 * Return the first index of a particular value. 
 */
public int index(int what){
  for (int i=0; i < count; i++) {
    if (data[i] == what) {
      return i;
    }
  }
  return -1;
}
