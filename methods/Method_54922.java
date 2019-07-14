@NativeType("b3SharedMemoryCommandHandle") public static long b3CalculateMassMatrixCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId,@NativeType("double const *") DoubleBuffer jointPositionsQ){
  if (CHECKS) {
    check(physClient);
  }
  return nb3CalculateMassMatrixCommandInit(physClient,bodyUniqueId,memAddress(jointPositionsQ));
}
