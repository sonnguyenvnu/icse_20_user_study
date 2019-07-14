@NativeType("CUresult") public static int cuModuleGetSurfRef(@NativeType("CUsurfref *") PointerBuffer pSurfRef,@NativeType("CUmodule") long hmod,@NativeType("char const *") CharSequence name){
  if (CHECKS) {
    check(pSurfRef,1);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(name,true);
    long nameEncoded=stack.getPointerAddress();
    return ncuModuleGetSurfRef(memAddress(pSurfRef),hmod,nameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
