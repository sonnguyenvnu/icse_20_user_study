@NativeType("b3SharedMemoryCommandHandle") public static long b3InitPhysicsParamCommand2(@NativeType("b3SharedMemoryCommandHandle") long commandHandle){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3InitPhysicsParamCommand2(commandHandle);
}
