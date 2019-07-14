public static int b3StateLoggingSetBodyBUniqueId(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyBUniqueId){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3StateLoggingSetBodyBUniqueId(commandHandle,bodyBUniqueId);
}
