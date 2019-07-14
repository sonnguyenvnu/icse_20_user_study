public static void b3SetClosestDistanceFilterBodyB(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueIdB){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3SetClosestDistanceFilterBodyB(commandHandle,bodyUniqueIdB);
}
