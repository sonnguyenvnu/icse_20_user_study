public static void b3UserDebugItemSetParentObject(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int objectUniqueId,int linkIndex){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3UserDebugItemSetParentObject(commandHandle,objectUniqueId,linkIndex);
}
