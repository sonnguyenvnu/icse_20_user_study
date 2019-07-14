public static int b3GetDebugItemUniqueId(@NativeType("b3SharedMemoryStatusHandle") long statusHandle){
  if (CHECKS) {
    check(statusHandle);
  }
  return nb3GetDebugItemUniqueId(statusHandle);
}
