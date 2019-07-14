/** 
 * Describes the protocols adopted by a class.
 * @param cls the class you want to inspect
 * @return an array of pointers of type Protocol* describing the protocols adopted by the class. Any protocols adopted by superclasses or other protocols are notincluded. The array contains  {@code *outCount} pointers followed by a {@code NULL} terminator. You must free the array with free().<p>If  {@code cls} adopts no protocols, or {@code cls} is Nil, returns {@code NULL} and {@code *outCount} is 0.</p>
 */
@Nullable @NativeType("Protocol **") public static PointerBuffer class_copyProtocolList(@NativeType("Class") long cls){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nclass_copyProtocolList(cls,memAddress(outCount));
    return memPointerBufferSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
