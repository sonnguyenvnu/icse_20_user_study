/** 
 * Takes a "handle" of a dynamic library returned by  {@link #dlopen} and the null-terminated symbol name, returning the address where that symbol is loadedinto memory. If the symbol is not found, in the specified library or any of the libraries that were automatically loaded by  {@link #dlopen} when thatlibrary was loaded,  {@code dlsym()} returns {@code NULL}.
 * @param handle the dynamic library handle
 * @param name   the symbol name
 */
@NativeType("void *") public static long dlsym(@NativeType("void *") long handle,@NativeType("char const *") CharSequence name){
  if (CHECKS) {
    check(handle);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(name,true);
    long nameEncoded=stack.getPointerAddress();
    return ndlsym(handle,nameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
