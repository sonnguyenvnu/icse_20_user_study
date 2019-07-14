/** 
 * Unregisters a window class, freeing the memory required for the class.
 * @param lpClassName a null-terminated string or a class atom. If {@code lpClassName} is a string, it specifies the window class name. This class name must have beenregistered by a previous call to the  {@link #RegisterClassEx} function. System classes, such as dialog box controls, cannot be unregistered. If thisparameter is an atom, it must be a class atom created by a previous call to the  {@link #RegisterClassEx} function. The atom must be in the low-orderword of  {@code lpClassName}; the high-order word must be zero.
 * @param hInstance   a handle to the instance of the module that created the class
 */
@NativeType("BOOL") public static boolean UnregisterClass(@NativeType("LPCTSTR") CharSequence lpClassName,@NativeType("HINSTANCE") long hInstance){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF16(lpClassName,true);
    long lpClassNameEncoded=stack.getPointerAddress();
    return nUnregisterClass(lpClassNameEncoded,hInstance) != 0;
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
