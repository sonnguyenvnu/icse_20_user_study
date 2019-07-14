/** 
 * Encodes the specified strings with the specified  {@link Encoder} and stores an array of pointers to the encoded data on the specified{@link MemoryStack}. A second array that contains the string lengths is stored immediately after the pointer array. Length values are 4-byte integers. <p>The encoded buffers must be freed with  {@link #apiArrayFree}.</p>
 * @param stack   the stack to use
 * @param encoder the encoder to use
 * @param strings the strings to encode
 * @return the pointer array address on the stack
 */
public static long apiArrayi(MemoryStack stack,Encoder encoder,CharSequence... strings){
  PointerBuffer pointers=stack.mallocPointer(strings.length);
  IntBuffer lengths=stack.mallocInt(strings.length);
  for (  CharSequence s : strings) {
    ByteBuffer buffer=encoder.encode(s,false);
    pointers.put(buffer);
    lengths.put(buffer.capacity());
  }
  return pointers.address;
}
