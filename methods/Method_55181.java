/** 
 * Returns the Ivar for a specified class variable of a given class.
 * @param cls  the class definition whose class variable you wish to obtain
 * @param name the name of the class variable definition to obtain
 * @return a pointer to an Ivar data structure containing information about the class variable specified by name
 */
@NativeType("Ivar") public static long class_getClassVariable(@NativeType("Class") long cls,@NativeType("char const *") CharSequence name){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF8(name,true);
    long nameEncoded=stack.getPointerAddress();
    return nclass_getClassVariable(cls,nameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
