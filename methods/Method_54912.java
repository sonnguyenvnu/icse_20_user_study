public static int b3PhysicsParamSetSolverResidualThreshold(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double solverResidualThreshold){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParamSetSolverResidualThreshold(commandHandle,solverResidualThreshold);
}
