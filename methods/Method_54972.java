public static int b3LoadSoftBodySetScale(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double scale){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3LoadSoftBodySetScale(commandHandle,scale);
}
