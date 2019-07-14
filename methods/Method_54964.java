@NativeType("b3SharedMemoryCommandHandle") public static long b3CreateRaycastCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,double rayFromWorldX,double rayFromWorldY,double rayFromWorldZ,double rayToWorldX,double rayToWorldY,double rayToWorldZ){
  if (CHECKS) {
    check(physClient);
  }
  return nb3CreateRaycastCommandInit(physClient,rayFromWorldX,rayFromWorldY,rayFromWorldZ,rayToWorldX,rayToWorldY,rayToWorldZ);
}
