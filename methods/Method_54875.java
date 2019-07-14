@NativeType("b3SharedMemoryCommandHandle") public static long b3InitUserDebugAddParameter(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("char const *") CharSequence txt,double rangeMin,double rangeMax,double startValue){
  if (CHECKS) {
    check(physClient);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(txt,true);
    long txtEncoded=stack.getPointerAddress();
    return nb3InitUserDebugAddParameter(physClient,txtEncoded,rangeMin,rangeMax,startValue);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
