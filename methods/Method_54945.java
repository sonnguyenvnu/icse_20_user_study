public static void b3CreateVisualSetFlag(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int shapeIndex,int flags){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3CreateVisualSetFlag(commandHandle,shapeIndex,flags);
}
