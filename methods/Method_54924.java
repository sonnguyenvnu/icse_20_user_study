public static void b3CalculateInverseKinematicsAddTargetPurePosition(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int endEffectorLinkIndex,@NativeType("double const *") DoubleBuffer targetPosition){
  if (CHECKS) {
    check(commandHandle);
    check(targetPosition,3);
  }
  nb3CalculateInverseKinematicsAddTargetPurePosition(commandHandle,endEffectorLinkIndex,memAddress(targetPosition));
}
