/** 
 * Describes the instance variables declared by a class.
 * @param cls the class to inspect
 * @return an array of pointers of type Ivar describing the instance variables declared by the class. Any instance variables declared by superclasses are notincluded. The array contains  {@code *outCount} pointers followed by a {@code NULL} terminator. You must free the array with free().<p>If the class declares no instance variables, or  {@code cls} is Nil, {@code NULL} is returned and {@code *outCount} is 0.</p>
 */
@Nullable @NativeType("Ivar *") public static PointerBuffer class_copyIvarList(@NativeType("Class") long cls){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nclass_copyIvarList(cls,memAddress(outCount));
    return memPointerBufferSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
