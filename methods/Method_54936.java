@NativeType("b3SharedMemoryCommandHandle") public static long b3JointControlCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,int controlMode){
  if (CHECKS) {
    check(physClient);
  }
  return nb3JointControlCommandInit(physClient,controlMode);
}
