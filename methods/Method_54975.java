public static int b3SetVRCameraTrackingObjectFlag(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int flag){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3SetVRCameraTrackingObjectFlag(commandHandle,flag);
}
