public static void b3UserDebugItemSetReplaceItemUniqueId(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int replaceItem){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3UserDebugItemSetReplaceItemUniqueId(commandHandle,replaceItem);
}
