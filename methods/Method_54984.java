public static void b3PushProfileTiming(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("char const *") ByteBuffer timingName){
  if (CHECKS) {
    check(physClient);
    checkNT1(timingName);
  }
  nb3PushProfileTiming(physClient,memAddress(timingName));
}
