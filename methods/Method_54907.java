public static int b3PhysicsParamSetSplitImpulsePenetrationThreshold(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double splitImpulsePenetrationThreshold){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParamSetSplitImpulsePenetrationThreshold(commandHandle,splitImpulsePenetrationThreshold);
}
