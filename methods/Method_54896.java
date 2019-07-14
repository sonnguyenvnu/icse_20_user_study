public static void b3SetClosestDistanceThreshold(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double distance){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3SetClosestDistanceThreshold(commandHandle,distance);
}
