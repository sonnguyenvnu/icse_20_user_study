public static void b3CalculateInverseKinematicsSetMaxNumIterations(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int maxNumIterations){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3CalculateInverseKinematicsSetMaxNumIterations(commandHandle,maxNumIterations);
}
