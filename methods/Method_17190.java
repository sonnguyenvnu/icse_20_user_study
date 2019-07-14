/** 
 * An ordered store(store + StoreStore barrier) of an element to a given offset
 * @param buffer this.buffer
 * @param offset computed via {@link UnsafeRefArrayAccess#calcElementOffset}
 * @param e an orderly kitty
 */
public static <E>void soElement(E[] buffer,long offset,E e){
  UNSAFE.putOrderedObject(buffer,offset,e);
}
