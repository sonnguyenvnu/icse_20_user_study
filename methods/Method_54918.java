@NativeType("b3SharedMemoryCommandHandle") public static long b3LoadBulletCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("char const *") CharSequence fileName){
  if (CHECKS) {
    check(physClient);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(fileName,true);
    long fileNameEncoded=stack.getPointerAddress();
    return nb3LoadBulletCommandInit(physClient,fileNameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
