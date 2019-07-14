public static void b3PushProfileTiming(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("char const *") CharSequence timingName){
  if (CHECKS) {
    check(physClient);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(timingName,true);
    long timingNameEncoded=stack.getPointerAddress();
    nb3PushProfileTiming(physClient,timingNameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
