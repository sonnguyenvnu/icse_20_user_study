public static int b3PhysicsParameterSetMinimumSolverIslandSize(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int minimumSolverIslandSize){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParameterSetMinimumSolverIslandSize(commandHandle,minimumSolverIslandSize);
}
