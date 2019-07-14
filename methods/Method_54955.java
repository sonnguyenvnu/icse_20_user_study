public static int b3CreatePoseCommandSetBasePosition(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double startPosX,double startPosY,double startPosZ){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3CreatePoseCommandSetBasePosition(commandHandle,startPosX,startPosY,startPosZ);
}
