public static void b3SetClosestDistanceFilterLinkB(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int linkIndexB){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3SetClosestDistanceFilterLinkB(commandHandle,linkIndexB);
}
