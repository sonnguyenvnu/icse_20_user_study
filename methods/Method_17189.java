/** 
 * A plain store (no ordering/fences) of an element to a given offset
 * @param buffer this.buffer
 * @param offset computed via {@link UnsafeRefArrayAccess#calcElementOffset(long)}
 * @param e an orderly kitty
 */
public static <E>void spElement(E[] buffer,long offset,E e){
  UNSAFE.putObject(buffer,offset,e);
}
