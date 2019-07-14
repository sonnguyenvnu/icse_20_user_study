@NativeType("b3SharedMemoryCommandHandle") public static long b3InitResetSimulationCommand2(@NativeType("b3SharedMemoryCommandHandle") long commandHandle){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3InitResetSimulationCommand2(commandHandle);
}
