@NativeType("b3SharedMemoryCommandHandle") public static long b3CalculateInverseDynamicsCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId,@NativeType("double const *") DoubleBuffer jointPositionsQ,@NativeType("double const *") DoubleBuffer jointVelocitiesQdot,@NativeType("double const *") DoubleBuffer jointAccelerations){
  if (CHECKS) {
    check(physClient);
  }
  return nb3CalculateInverseDynamicsCommandInit(physClient,bodyUniqueId,memAddress(jointPositionsQ),memAddress(jointVelocitiesQdot),memAddress(jointAccelerations));
}
