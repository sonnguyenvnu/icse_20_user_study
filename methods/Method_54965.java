public static void b3RaycastBatchSetNumThreads(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int numThreads){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3RaycastBatchSetNumThreads(commandHandle,numThreads);
}
