@NativeType("b3SharedMemoryCommandHandle") public static long b3CollisionFilterCommandInit(@NativeType("b3PhysicsClientHandle") long physClient){
  if (CHECKS) {
    check(physClient);
  }
  return nb3CollisionFilterCommandInit(physClient);
}
