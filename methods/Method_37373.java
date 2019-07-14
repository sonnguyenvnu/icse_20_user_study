/** 
 * Appends single  {@code float} to buffer.
 */
public void append(final float element){
  if (offset - buffer.length >= 0) {
    grow(offset);
  }
  buffer[offset++]=element;
}
