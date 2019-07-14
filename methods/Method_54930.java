public static void b3CalculateInverseKinematicsSetCurrentPositions(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("double const *") DoubleBuffer currentJointPositions){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3CalculateInverseKinematicsSetCurrentPositions(commandHandle,currentJointPositions.remaining(),memAddress(currentJointPositions));
}
