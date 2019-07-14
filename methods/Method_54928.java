public static void b3CalculateInverseKinematicsSelectSolver(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int solver){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3CalculateInverseKinematicsSelectSolver(commandHandle,solver);
}
