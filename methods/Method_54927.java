public static void b3CalculateInverseKinematicsSetJointDamping(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("double const *") DoubleBuffer jointDampingCoeff){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3CalculateInverseKinematicsSetJointDamping(commandHandle,jointDampingCoeff.remaining(),memAddress(jointDampingCoeff));
}
