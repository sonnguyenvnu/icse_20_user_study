/** 
 * Vararg version of  {@link #mallocInt}. 
 */
public IntBuffer ints(int... values){
  IntBuffer buffer=mallocInt(values.length).put(values);
  buffer.flip();
  return buffer;
}
