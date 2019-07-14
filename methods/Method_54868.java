@NativeType("b3SharedMemoryCommandHandle") public static long b3InitRequestDebugLinesCommand(@NativeType("b3PhysicsClientHandle") long physClient,int debugMode){
  if (CHECKS) {
    check(physClient);
  }
  return nb3InitRequestDebugLinesCommand(physClient,debugMode);
}
