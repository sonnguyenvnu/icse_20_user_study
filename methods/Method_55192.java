/** 
 * Describes the properties declared by a class.
 * @param cls the class you want to inspect
 * @return an array of pointers of type {@code objc_property_t} describing the properties declared by the class. Any properties declared by superclasses are notincluded. The array contains  {@code *outCount} pointers followed by a {@code NULL} terminator. You must free the array with free().<p>If  {@code cls} declares no properties, or {@code cls} is Nil, returns {@code NULL} and {@code *outCount} is 0.</p>
 */
@Nullable @NativeType("objc_property_t *") public static PointerBuffer class_copyPropertyList(@NativeType("Class") long cls){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nclass_copyPropertyList(cls,memAddress(outCount));
    return memPointerBufferSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
