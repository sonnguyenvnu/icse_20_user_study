/** 
 * PointerBuffer version of  {@link #memAlloc}.
 * @param size the number of pointer values to allocate.
 */
public static PointerBuffer memAllocPointer(int size){
  return Pointer.Default.wrap(PointerBuffer.class,nmemAllocChecked(getAllocationSize(size,POINTER_SHIFT)),size);
}
