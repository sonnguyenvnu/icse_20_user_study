public static int b3StateLoggingSetLogFlags(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int logFlags){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3StateLoggingSetLogFlags(commandHandle,logFlags);
}
