/** 
 * Returns the next byte in the  {@link BufferChain}. <p> This advances the current position by one. <p>
 * @return the next byte in the {@link BufferChain}
 * @throws BufferUnderflowException if the buffer chain has been exhausted.
 */
public byte get() throws BufferUnderflowException {
  byte res=remain(SIZE_BYTE).get();
  return res;
}
