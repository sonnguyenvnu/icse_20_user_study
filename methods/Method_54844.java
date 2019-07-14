public static int b3GetStatusAABB(@NativeType("b3SharedMemoryStatusHandle") long statusHandle,int linkIndex,@NativeType("double *") DoubleBuffer aabbMin,@NativeType("double *") DoubleBuffer aabbMax){
  if (CHECKS) {
    check(statusHandle);
    check(aabbMin,3);
    check(aabbMax,3);
  }
  return nb3GetStatusAABB(statusHandle,linkIndex,memAddress(aabbMin),memAddress(aabbMax));
}
