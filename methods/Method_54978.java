public static int b3StateLoggingSetBodyAUniqueId(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyAUniqueId){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3StateLoggingSetBodyAUniqueId(commandHandle,bodyAUniqueId);
}
