/** 
 * Stores the addresses of the specified array of buffers on the specified  {@link MemoryStack}. A second array that contains the buffer remaining bytes is stored immediately after the pointer array. Length values are pointer-sized integers.
 * @param stack   the stack to use
 * @param buffers the buffers to store
 * @return the pointer array address on the stack
 */
public static long apiArrayp(MemoryStack stack,ByteBuffer... buffers){
  long pointers=apiArray(stack,buffers);
  PointerBuffer lengths=stack.mallocPointer(buffers.length);
  for (  ByteBuffer buffer : buffers) {
    lengths.put(buffer.remaining());
  }
  return pointers;
}
