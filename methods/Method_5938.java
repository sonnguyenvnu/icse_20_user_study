/** 
 * Moves the reading offset by  {@code bytes}.
 * @param bytes The number of bytes to skip.
 * @throws IllegalArgumentException Thrown if the new position is neither in nor at the end of thearray.
 */
public void skipBytes(int bytes){
  setPosition(position + bytes);
}
