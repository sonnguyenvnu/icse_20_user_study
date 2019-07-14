public static int b3GetStatusBodyIndex(@NativeType("b3SharedMemoryStatusHandle") long statusHandle){
  if (CHECKS) {
    check(statusHandle);
  }
  return nb3GetStatusBodyIndex(statusHandle);
}
