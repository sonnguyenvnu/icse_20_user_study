@NativeType("b3SharedMemoryCommandHandle") public static long b3CreatePoseCommandInit2(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3CreatePoseCommandInit2(commandHandle,bodyUniqueId);
}
