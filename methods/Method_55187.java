/** 
 * Describes the instance methods implemented by a class.
 * @param cls the class you want to inspect
 * @return an array of pointers of type Method describing the instance methods implemented by the class—any instance methods implemented by superclasses are notincluded. The array contains  {@code *outCount} pointers followed by a {@code NULL} terminator. You must free the array with free().<p>If  {@code cls} implements no instance methods, or {@code cls} is Nil, returns {@code NULL} and {@code *outCount} is 0.</p>
 */
@Nullable @NativeType("Method *") public static PointerBuffer class_copyMethodList(@NativeType("Class") long cls){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nclass_copyMethodList(cls,memAddress(outCount));
    return memPointerBufferSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
