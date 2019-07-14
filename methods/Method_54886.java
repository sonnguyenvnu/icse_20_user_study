public static void b3RequestCameraImageSelectRenderer(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int renderer){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3RequestCameraImageSelectRenderer(commandHandle,renderer);
}
