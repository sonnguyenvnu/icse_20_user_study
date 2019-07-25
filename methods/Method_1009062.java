/** 
 * Removes all of the elements from this list.  The list will be empty after this call returns.
 */
public void clear(){
  for (int i=0; i < this.size; i++) {
    this.openElements[i]=null;
  }
  this.size=0;
}
