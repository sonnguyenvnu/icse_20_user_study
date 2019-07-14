public static void b3CalculateInverseKinematicsSetResidualThreshold(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double residualThreshold){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3CalculateInverseKinematicsSetResidualThreshold(commandHandle,residualThreshold);
}
