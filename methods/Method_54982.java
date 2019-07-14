@NativeType("b3SharedMemoryCommandHandle") public static long b3ProfileTimingCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("char const *") CharSequence name){
  if (CHECKS) {
    check(physClient);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(name,true);
    long nameEncoded=stack.getPointerAddress();
    return nb3ProfileTimingCommandInit(physClient,nameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
