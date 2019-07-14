public static int b3CreateSensorEnableIMUForLink(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int linkIndex,@NativeType("int") boolean enable){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3CreateSensorEnableIMUForLink(commandHandle,linkIndex,enable ? 1 : 0);
}
