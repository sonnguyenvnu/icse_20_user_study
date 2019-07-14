public static void b3SetContactFilterBodyA(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueIdA){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3SetContactFilterBodyA(commandHandle,bodyUniqueIdA);
}
