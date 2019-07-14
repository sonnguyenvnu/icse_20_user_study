/** 
 * Vararg version of  {@link #mallocLong}. 
 */
public LongBuffer longs(long... more){
  LongBuffer buffer=mallocLong(more.length).put(more);
  buffer.flip();
  return buffer;
}
