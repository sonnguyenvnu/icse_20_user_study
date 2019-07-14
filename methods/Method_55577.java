/** 
 * Vararg version of  {@link #mallocPointer}. 
 */
public PointerBuffer pointers(Buffer... values){
  PointerBuffer buffer=mallocPointer(values.length);
  for (int i=0; i < values.length; i++) {
    buffer.put(i,memAddress(values[i]));
  }
  return buffer;
}
