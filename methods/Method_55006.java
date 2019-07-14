/** 
 * Creates a new  {@code DLSyms} object.
 * @param libPath the dynamic library path
 */
@NativeType("DLSyms *") public static long dlSymsInit(@NativeType("char const *") CharSequence libPath){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(libPath,true);
    long libPathEncoded=stack.getPointerAddress();
    return ndlSymsInit(libPathEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
