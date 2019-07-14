/** 
 * Returns an array of all the protocols known to the runtime.
 * @return a C array of all the protocols known to the runtime. The array contains {@code *outCount} pointers followed by a {@code NULL} terminator. You must free thelist with free().
 */
@Nullable @NativeType("Protocol **") public static PointerBuffer objc_copyProtocolList(){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nobjc_copyProtocolList(memAddress(outCount));
    return memPointerBufferSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
