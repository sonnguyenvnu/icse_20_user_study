/** 
 * Creates a  {@link ByteBuffer} instance as a view of the specified {@link CustomBuffer} between its current position and limit.<p>The returned  {@code ByteBuffer} instance will be set to the native {@link ByteOrder}.</p>
 * @param buffer the source buffer
 * @return the {@code ByteBuffer} view
 */
public static ByteBuffer memByteBuffer(CustomBuffer<?> buffer){
  if (CHECKS && (Integer.MAX_VALUE / buffer.sizeof()) < buffer.remaining()) {
    throw new IllegalArgumentException("The source buffer range is too wide");
  }
  return wrap(BUFFER_BYTE,memAddress(buffer),buffer.remaining() * buffer.sizeof()).order(NATIVE_ORDER);
}
