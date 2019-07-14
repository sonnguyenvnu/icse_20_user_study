/** 
 * Returns string at given position.
 */
public String stringAt(final int index){
  if (index >= this.index) {
    throw new ArrayIndexOutOfBoundsException();
  }
  return array[index];
}
