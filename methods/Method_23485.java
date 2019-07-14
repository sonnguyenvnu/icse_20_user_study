/** 
 * Returns the index of child who.
 */
public int getChildIndex(PShape who){
  for (int i=0; i < childCount; i++) {
    if (children[i] == who) {
      return i;
    }
  }
  return -1;
}
