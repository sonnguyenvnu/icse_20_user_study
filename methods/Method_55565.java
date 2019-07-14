/** 
 * Float version of  {@link #calloc(int)}. 
 */
public FloatBuffer callocFloat(int size){
  int bytes=size * 4;
  long address=nmalloc(4,bytes);
  memSet(address,0,bytes);
  return MemoryUtil.wrap(BUFFER_FLOAT,address,size);
}
