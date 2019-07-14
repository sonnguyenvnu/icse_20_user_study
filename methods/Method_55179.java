/** 
 * Returns the Ivar for a specified instance variable of a given class.
 * @param cls  the class whose instance variable you wish to obtain
 * @param name the name of the instance variable definition to obtain
 * @return a pointer to an Ivar data structure containing information about the instance variable specified by name
 */
@NativeType("Ivar") public static long class_getInstanceVariable(@NativeType("Class") long cls,@NativeType("char const *") CharSequence name){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF8(name,true);
    long nameEncoded=stack.getPointerAddress();
    return nclass_getInstanceVariable(cls,nameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
