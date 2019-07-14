@NativeType("b3SharedMemoryCommandHandle") public static long b3RequestMouseEventsCommandInit(@NativeType("b3PhysicsClientHandle") long physClient){
  if (CHECKS) {
    check(physClient);
  }
  return nb3RequestMouseEventsCommandInit(physClient);
}
