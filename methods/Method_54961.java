@NativeType("b3SharedMemoryCommandHandle") public static long b3PickBody(@NativeType("b3PhysicsClientHandle") long physClient,double rayFromWorldX,double rayFromWorldY,double rayFromWorldZ,double rayToWorldX,double rayToWorldY,double rayToWorldZ){
  if (CHECKS) {
    check(physClient);
  }
  return nb3PickBody(physClient,rayFromWorldX,rayFromWorldY,rayFromWorldZ,rayToWorldX,rayToWorldY,rayToWorldZ);
}
