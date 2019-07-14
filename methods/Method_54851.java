@NativeType("b3SharedMemoryCommandHandle") public static long b3GetDynamicsInfoCommandInit2(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkIndex){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3GetDynamicsInfoCommandInit2(commandHandle,bodyUniqueId,linkIndex);
}
