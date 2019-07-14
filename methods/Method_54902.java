@NativeType("b3SharedMemoryCommandHandle") public static long b3InitLoadTexture(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("char const *") CharSequence filename){
  if (CHECKS) {
    check(physClient);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(filename,true);
    long filenameEncoded=stack.getPointerAddress();
    return nb3InitLoadTexture(physClient,filenameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
