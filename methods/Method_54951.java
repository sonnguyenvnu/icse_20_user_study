public static void b3CreateMultiBodyUseMaximalCoordinates(@NativeType("b3SharedMemoryCommandHandle") long commandHandle){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3CreateMultiBodyUseMaximalCoordinates(commandHandle);
}
