public static void b3RequestCameraImageSetPixelResolution(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int width,int height){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3RequestCameraImageSetPixelResolution(commandHandle,width,height);
}
