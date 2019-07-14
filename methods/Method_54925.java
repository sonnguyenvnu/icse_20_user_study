public static void b3CalculateInverseKinematicsPosWithNullSpaceVel(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int endEffectorLinkIndex,@NativeType("double const *") DoubleBuffer targetPosition,@NativeType("double const *") DoubleBuffer lowerLimit,@NativeType("double const *") DoubleBuffer upperLimit,@NativeType("double const *") DoubleBuffer jointRange,@NativeType("double const *") DoubleBuffer restPose){
  if (CHECKS) {
    check(commandHandle);
    check(upperLimit,lowerLimit.remaining());
    check(jointRange,lowerLimit.remaining());
    check(restPose,lowerLimit.remaining());
    check(targetPosition,3);
  }
  nb3CalculateInverseKinematicsPosWithNullSpaceVel(commandHandle,lowerLimit.remaining(),endEffectorLinkIndex,memAddress(targetPosition),memAddress(lowerLimit),memAddress(upperLimit),memAddress(jointRange),memAddress(restPose));
}
