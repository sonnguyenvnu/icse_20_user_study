public static void b3RequestCameraImageSetShadow(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int hasShadow){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3RequestCameraImageSetShadow(commandHandle,hasShadow);
}
