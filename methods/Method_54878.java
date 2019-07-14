public static void b3SetDebugObjectColor(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int objectUniqueId,int linkIndex,@NativeType("double const *") DoubleBuffer objectColorRGB){
  if (CHECKS) {
    check(commandHandle);
    check(objectColorRGB,3);
  }
  nb3SetDebugObjectColor(commandHandle,objectUniqueId,linkIndex,memAddress(objectColorRGB));
}
