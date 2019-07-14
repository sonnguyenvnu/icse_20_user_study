/** 
 * Vararg version of  {@link #mallocDouble}. 
 */
public DoubleBuffer doubles(double... values){
  DoubleBuffer buffer=mallocDouble(values.length).put(values);
  buffer.flip();
  return buffer;
}
