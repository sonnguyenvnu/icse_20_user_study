/** 
 * Vararg version of  {@link #mallocShort}. 
 */
public ShortBuffer shorts(short... values){
  ShortBuffer buffer=mallocShort(values.length).put(values);
  buffer.flip();
  return buffer;
}
