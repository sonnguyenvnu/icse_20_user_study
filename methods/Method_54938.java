@NativeType("b3SharedMemoryCommandHandle") public static long b3JointControlCommandInit2Internal(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int controlMode){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3JointControlCommandInit2Internal(commandHandle,bodyUniqueId,controlMode);
}
