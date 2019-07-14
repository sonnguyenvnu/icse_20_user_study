/** 
 * Encodes the specified strings with the specified  {@link Encoder} and stores an array of pointers to the encoded data on the specified{@link MemoryStack}. The encoded strings include null-termination.
 * @param stack   the stack to use
 * @param encoder the encoder to use
 * @param strings the strings to encode
 * @return the pointer array address on the stack
 */
public static long apiArray(MemoryStack stack,Encoder encoder,CharSequence... strings){
  PointerBuffer pointers=stack.mallocPointer(strings.length);
  for (  CharSequence s : strings) {
    pointers.put(encoder.encode(s,true));
  }
  return pointers.address;
}
