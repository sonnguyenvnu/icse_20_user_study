/** 
 * Returns the metaclass definition of a specified class. <p>If the definition for the named class is not registered, this function calls the class handler callback and then checks a second time to see if the class is registered. However, every class definition must have a valid metaclass definition, and so the metaclass definition is always returned, whether it’s valid or not.</p>
 * @param name the name of the class to look up
 * @return the Class object for the metaclass of the named class, or {@link #nil} if the class is not registered with the Objective-C runtime
 */
@NativeType("Class") public static long objc_getMetaClass(@NativeType("char const *") CharSequence name){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF8(name,true);
    long nameEncoded=stack.getPointerAddress();
    return nobjc_getMetaClass(nameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
