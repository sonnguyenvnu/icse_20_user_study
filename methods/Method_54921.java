@NativeType("b3SharedMemoryCommandHandle") public static long b3CalculateJacobianCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId,int linkIndex,@NativeType("double const *") DoubleBuffer localPosition,@NativeType("double const *") DoubleBuffer jointPositionsQ,@NativeType("double const *") DoubleBuffer jointVelocitiesQdot,@NativeType("double const *") DoubleBuffer jointAccelerations){
  if (CHECKS) {
    check(physClient);
    check(localPosition,3);
  }
  return nb3CalculateJacobianCommandInit(physClient,bodyUniqueId,linkIndex,memAddress(localPosition),memAddress(jointPositionsQ),memAddress(jointVelocitiesQdot),memAddress(jointAccelerations));
}
