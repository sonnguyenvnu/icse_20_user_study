/** 
 * Calculates string length.
 */
protected int calculateLength(){
  int len=0;
  for (int i=0; i < index; i++) {
    len+=array[i].length();
  }
  return len;
}
