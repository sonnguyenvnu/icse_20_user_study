@NativeType("b3SharedMemoryCommandHandle") public static long b3RequestActualStateCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId){
  if (CHECKS) {
    check(physClient);
  }
  return nb3RequestActualStateCommandInit(physClient,bodyUniqueId);
}
