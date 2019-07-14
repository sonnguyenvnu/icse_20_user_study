@NativeType("b3SharedMemoryCommandHandle") public static long b3InitAABBOverlapQuery(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("double const *") DoubleBuffer aabbMin,@NativeType("double const *") DoubleBuffer aabbMax){
  if (CHECKS) {
    check(physClient);
    check(aabbMin,3);
    check(aabbMax,3);
  }
  return nb3InitAABBOverlapQuery(physClient,memAddress(aabbMin),memAddress(aabbMax));
}
