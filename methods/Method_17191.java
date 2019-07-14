/** 
 * A plain load (no ordering/fences) of an element from a given offset.
 * @param buffer this.buffer
 * @param offset computed via {@link UnsafeRefArrayAccess#calcElementOffset(long)}
 * @return the element at the offset
 */
@SuppressWarnings("unchecked") public static <E>E lpElement(E[] buffer,long offset){
  return (E)UNSAFE.getObject(buffer,offset);
}
