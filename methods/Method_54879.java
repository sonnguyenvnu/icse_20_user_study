public static void b3RemoveDebugObjectColor(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int objectUniqueId,int linkIndex){
  if (CHECKS) {
    check(commandHandle);
  }
  nb3RemoveDebugObjectColor(commandHandle,objectUniqueId,linkIndex);
}
