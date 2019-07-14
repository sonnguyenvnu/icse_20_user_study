/** 
 * Sets the reading offset in the array.
 * @param position Byte offset in the array from which to read.
 * @throws IllegalArgumentException Thrown if the new position is neither in nor at the end of thearray.
 */
public void setPosition(int position){
  Assertions.checkArgument(position >= 0 && position <= limit);
  this.position=position;
}
