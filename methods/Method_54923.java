@NativeType("b3SharedMemoryCommandHandle") public static long b3CalculateInverseKinematicsCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId){
  if (CHECKS) {
    check(physClient);
  }
  return nb3CalculateInverseKinematicsCommandInit(physClient,bodyUniqueId);
}
