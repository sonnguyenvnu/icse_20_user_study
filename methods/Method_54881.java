@NativeType("b3SharedMemoryCommandHandle") public static long b3InitRequestCameraImage2(@NativeType("b3SharedMemoryCommandHandle") long commandHandle){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3InitRequestCameraImage2(commandHandle);
}
