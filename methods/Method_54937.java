@NativeType("b3SharedMemoryCommandHandle") public static long b3JointControlCommandInit2(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId,int controlMode){
  if (CHECKS) {
    check(physClient);
  }
  return nb3JointControlCommandInit2(physClient,bodyUniqueId,controlMode);
}
