/** 
 * Creates and returns a list of pointers to all registered class definitions.
 * @return a {@link #nil} terminated array of classes. You must free the array with free()
 */
@Nullable @NativeType("Class *") public static PointerBuffer objc_copyClassList(){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nobjc_copyClassList(memAddress(outCount));
    return memPointerBufferSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
