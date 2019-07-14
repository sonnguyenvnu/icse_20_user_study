public static int b3ChangeDynamicsInfoSetLocalInertiaDiagonal(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkIndex,@NativeType("double const *") DoubleBuffer localInertiaDiagonal){
  if (CHECKS) {
    check(commandHandle);
    check(localInertiaDiagonal,3);
  }
  return nb3ChangeDynamicsInfoSetLocalInertiaDiagonal(commandHandle,bodyUniqueId,linkIndex,memAddress(localInertiaDiagonal));
}
