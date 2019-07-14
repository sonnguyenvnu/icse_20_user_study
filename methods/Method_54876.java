@NativeType("b3SharedMemoryCommandHandle") public static long b3InitUserDebugReadParameter(@NativeType("b3PhysicsClientHandle") long physClient,int debugItemUniqueId){
  if (CHECKS) {
    check(physClient);
  }
  return nb3InitUserDebugReadParameter(physClient,debugItemUniqueId);
}
