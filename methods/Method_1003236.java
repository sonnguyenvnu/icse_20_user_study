/** 
 * Shrink the array to this size.
 * @param size the new size
 */
public void truncate(int size){
  if (pos > size) {
    byte[] buff=Arrays.copyOf(data,size);
    this.pos=size;
    data=buff;
  }
}
