/** 
 * Int version of  {@link #calloc(int)}. 
 */
public IntBuffer callocInt(int size){
  int bytes=size * 4;
  long address=nmalloc(4,bytes);
  memSet(address,0,bytes);
  return MemoryUtil.wrap(BUFFER_INT,address,size);
}
