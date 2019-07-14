@NativeType("b3SharedMemoryCommandHandle") public static long b3RequestCollisionInfoCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId){
  if (CHECKS) {
    check(physClient);
  }
  return nb3RequestCollisionInfoCommandInit(physClient,bodyUniqueId);
}
