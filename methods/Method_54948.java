@NativeType("b3SharedMemoryCommandHandle") public static long b3CreateMultiBodyCommandInit(@NativeType("b3PhysicsClientHandle") long physClient){
  if (CHECKS) {
    check(physClient);
  }
  return nb3CreateMultiBodyCommandInit(physClient);
}
