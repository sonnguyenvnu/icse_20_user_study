public static void b3CalculateInverseKinematicsPosOrnWithNullSpaceVel(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int endEffectorLinkIndex,@NativeType("double const *") DoubleBuffer targetPosition,@NativeType("double const *") DoubleBuffer targetOrientation,@NativeType("double const *") DoubleBuffer lowerLimit,@NativeType("double const *") DoubleBuffer upperLimit,@NativeType("double const *") DoubleBuffer jointRange,@NativeType("double const *") DoubleBuffer restPose){
  if (CHECKS) {
    check(commandHandle);
    check(upperLimit,lowerLimit.remaining());
    check(jointRange,lowerLimit.remaining());
    check(restPose,lowerLimit.remaining());
    check(targetPosition,3);
    check(targetOrientation,4);
  }
  nb3CalculateInverseKinematicsPosOrnWithNullSpaceVel(commandHandle,lowerLimit.remaining(),endEffectorLinkIndex,memAddress(targetPosition),memAddress(targetOrientation),memAddress(lowerLimit),memAddress(upperLimit),memAddress(jointRange),memAddress(restPose));
}
