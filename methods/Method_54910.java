public static int b3PhysicsParameterSetDeterministicOverlappingPairs(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int deterministicOverlappingPairs){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParameterSetDeterministicOverlappingPairs(commandHandle,deterministicOverlappingPairs);
}
