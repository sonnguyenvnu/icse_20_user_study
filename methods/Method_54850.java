@NativeType("b3SharedMemoryCommandHandle") public static long b3GetDynamicsInfoCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId,int linkIndex){
  if (CHECKS) {
    check(physClient);
  }
  return nb3GetDynamicsInfoCommandInit(physClient,bodyUniqueId,linkIndex);
}
