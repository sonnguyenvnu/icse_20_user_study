/** 
 * A volatile load (load + LoadLoad barrier) of an element from a given offset.
 * @param buffer this.buffer
 * @param offset computed via {@link UnsafeRefArrayAccess#calcElementOffset(long)}
 * @return the element at the offset
 */
@SuppressWarnings("unchecked") public static <E>E lvElement(E[] buffer,long offset){
  return (E)UNSAFE.getObjectVolatile(buffer,offset);
}
