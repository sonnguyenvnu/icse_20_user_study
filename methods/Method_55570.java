/** 
 * Vararg version of  {@link #mallocFloat}. 
 */
public FloatBuffer floats(float... values){
  FloatBuffer buffer=mallocFloat(values.length).put(values);
  buffer.flip();
  return buffer;
}
