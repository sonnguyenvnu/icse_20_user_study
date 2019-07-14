/** 
 * Creates a new class and metaclass. <p>You can get a pointer to the new metaclass by calling  {@code object_getClass(newClass)}.</p> <p>To create a new class, start by calling objc_allocateClassPair. Then set the class's attributes with functions like  {@link #class_addMethod} and{@link #class_addIvar}. When you are done building the class, call  {@link #objc_registerClassPair}. The new class is now ready for use.</p> <p>Instance methods and instance variables should be added to the class itself. Class methods should be added to the metaclass.</p>
 * @param superclass the class to use as the new class's superclass, or Nil to create a new root class
 * @param name       the string to use as the new class's name. The string will be copied.
 * @param extraBytes the number of bytes to allocate for indexed ivars at the end of the class and metaclass objects. This should usually be 0.
 * @return the new class, or Nil if the class could not be created (for example, the desired name is already in use)
 */
@NativeType("Class") public static long objc_allocateClassPair(@NativeType("Class") long superclass,@NativeType("char const *") CharSequence name,@NativeType("size_t") long extraBytes){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF8(name,true);
    long nameEncoded=stack.getPointerAddress();
    return nobjc_allocateClassPair(superclass,nameEncoded,extraBytes);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
