public static void b3RaycastBatchSetParentObject(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int parentObjectUniqueId,int parentLinkIndex){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3RaycastBatchSetParentObject(commandHandle,parentObjectUniqueId,parentLinkIndex);
}
