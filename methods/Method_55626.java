/** 
 * PointerBuffer version of  {@link #memRealloc}.
 * @param size the number of pointer values to allocate.
 */
public static PointerBuffer memRealloc(@Nullable PointerBuffer ptr,int size){
  PointerBuffer buffer=memPointerBuffer(nmemReallocChecked(ptr == null ? NULL : ptr.address,getAllocationSize(size,POINTER_SHIFT)),size);
  if (ptr != null) {
    buffer.position(min(ptr.position(),size));
  }
  return buffer;
}
