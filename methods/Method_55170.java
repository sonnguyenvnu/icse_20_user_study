/** 
 * Returns the class definition of a specified class. <p>This function is the same as  {@link #objc_getClass}, but kills the process if the class is not found.</p> <p>This function is used by ZeroLink, where failing to find a class would be a compile-time link error without ZeroLink.</p>
 * @param name the name of the class to look up
 * @return the Class object for the named class
 */
@NativeType("Class") public static long objc_getRequiredClass(@NativeType("char const *") CharSequence name){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF8(name,true);
    long nameEncoded=stack.getPointerAddress();
    return nobjc_getRequiredClass(nameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
