public static int b3PhysicsParamSetEnableFileCaching(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("int") boolean enableFileCaching){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParamSetEnableFileCaching(commandHandle,enableFileCaching ? 1 : 0);
}
