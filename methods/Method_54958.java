public static int b3RequestActualStateCommandComputeLinkVelocity(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int computeLinkVelocity){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3RequestActualStateCommandComputeLinkVelocity(commandHandle,computeLinkVelocity);
}
