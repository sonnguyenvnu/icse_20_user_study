/** 
 * Stores the specified array of pointer addresses on the specified  {@link MemoryStack}.
 * @param stack     the stack to use
 * @param addresses the pointer addresses to store
 * @return the pointer array address on the stack
 */
public static long apiArray(MemoryStack stack,long... addresses){
  PointerBuffer pointers=memPointerBuffer(stack.nmalloc(POINTER_SIZE,addresses.length << POINTER_SHIFT),addresses.length);
  for (  long address : addresses) {
    pointers.put(address);
  }
  return pointers.address;
}
