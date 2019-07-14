public static int b3PhysicsParameterSetEnableSAT(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("int") boolean enableSAT){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParameterSetEnableSAT(commandHandle,enableSAT ? 1 : 0);
}
