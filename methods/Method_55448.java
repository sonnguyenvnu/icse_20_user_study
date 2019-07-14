/** 
 * Reads the pointer at this buffer's current position, and then increments the position. The pointer is returned as a  {@link ByteBuffer} instance thatstarts at the pointer address and has capacity equal to the specified  {@code size}.
 * @throws BufferUnderflowException If the buffer's current position is not smaller than its limit
 */
public ByteBuffer getByteBuffer(int size){
  return memByteBuffer(get(),size);
}
