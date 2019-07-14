public static int b3RequestActualStateCommandComputeForwardKinematics(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int computeForwardKinematics){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3RequestActualStateCommandComputeForwardKinematics(commandHandle,computeForwardKinematics);
}
