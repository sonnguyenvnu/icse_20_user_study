/** 
 * Returns the names of all the classes within a specified library or framework.
 * @param image the library or framework you are inquiring about
 * @return an array of C strings representing all of the class names within the specified library or framework
 */
@Nullable @NativeType("char const **") public static PointerBuffer objc_copyClassNamesForImage(@NativeType("char const *") ByteBuffer image){
  if (CHECKS) {
    checkNT1(image);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nobjc_copyClassNamesForImage(memAddress(image),memAddress(outCount));
    return memPointerBufferSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
