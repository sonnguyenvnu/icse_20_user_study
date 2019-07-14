/** 
 * Returns a pointer to a symbol with name  {@code pSymbolName} in the library with handle {@code pLib}, or returns a null pointer if the symbol cannot be found.
 * @param pLib        the dynamic library
 * @param pSymbolName the symbol name
 */
@NativeType("void *") public static long dlFindSymbol(@NativeType("DLLib *") long pLib,@NativeType("char const *") CharSequence pSymbolName){
  if (CHECKS) {
    check(pLib);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(pSymbolName,true);
    long pSymbolNameEncoded=stack.getPointerAddress();
    return ndlFindSymbol(pLib,pSymbolNameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
