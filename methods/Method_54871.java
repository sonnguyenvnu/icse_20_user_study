public static void b3UserDebugTextSetOptionFlags(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int optionFlags){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3UserDebugTextSetOptionFlags(commandHandle,optionFlags);
}
