/** 
 * DoubleBuffer version of  {@link #memAlloc}.
 * @param size the number of double values to allocate.
 */
public static DoubleBuffer memAllocDouble(int size){
  return wrap(BUFFER_DOUBLE,nmemAllocChecked(getAllocationSize(size,3)),size);
}
