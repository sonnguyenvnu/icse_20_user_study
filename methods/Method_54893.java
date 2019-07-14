public static void b3SetClosestDistanceFilterBodyA(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueIdA){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3SetClosestDistanceFilterBodyA(commandHandle,bodyUniqueIdA);
}
