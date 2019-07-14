/** 
 * Returns an array of the properties declared by a protocol.
 * @param proto a protocol
 * @return a C array of pointers of type objc_property_t describing the properties declared by {@code proto}. Any properties declared by other protocols adopted by this protocol are not included. The array contains  {@code *outCount} pointers followed by a {@code NULL} terminator. You must free the array with free().<p>If the protocol declares no properties,  {@code NULL} is returned and {@code *outCount} is 0.</p>
 */
@Nullable @NativeType("objc_property_t *") public static PointerBuffer protocol_copyPropertyList(@NativeType("Protocol *") long proto){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nprotocol_copyPropertyList(proto,memAddress(outCount));
    return memPointerBufferSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
