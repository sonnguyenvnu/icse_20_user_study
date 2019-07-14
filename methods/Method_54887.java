public static void b3RequestCameraImageSetFlags(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int flags){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3RequestCameraImageSetFlags(commandHandle,flags);
}
