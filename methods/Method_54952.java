public static void b3CreateMultiBodySetFlags(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int flags){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3CreateMultiBodySetFlags(commandHandle,flags);
}
