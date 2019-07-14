@NativeType("b3SharedMemoryCommandHandle") public static long b3InitUserDebugAddParameter(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("char const *") ByteBuffer txt,double rangeMin,double rangeMax,double startValue){
  if (CHECKS) {
    check(physClient);
    checkNT1(txt);
  }
  return nb3InitUserDebugAddParameter(physClient,memAddress(txt),rangeMin,rangeMax,startValue);
}
