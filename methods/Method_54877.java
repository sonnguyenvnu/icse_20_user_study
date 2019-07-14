@NativeType("b3SharedMemoryCommandHandle") public static long b3InitUserDebugDrawRemove(@NativeType("b3PhysicsClientHandle") long physClient,int debugItemUniqueId){
  if (CHECKS) {
    check(physClient);
  }
  return nb3InitUserDebugDrawRemove(physClient,debugItemUniqueId);
}
