/** 
 * Returns a  {@link ByteBuffer} instance that starts at the address found at the specified {@code index} and has capacity equal to the specified size. 
 */
public ByteBuffer getByteBuffer(int index,int size){
  return memByteBuffer(get(index),size);
}
