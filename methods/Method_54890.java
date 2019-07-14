@NativeType("b3SharedMemoryCommandHandle") public static long b3InitRequestContactPointInformation(@NativeType("b3PhysicsClientHandle") long physClient){
  if (CHECKS) {
    check(physClient);
  }
  return nb3InitRequestContactPointInformation(physClient);
}
