public static void b3ApplyExternalForce(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkId,@NativeType("double const *") DoubleBuffer force,@NativeType("double const *") DoubleBuffer position,int flag){
  if (CHECKS) {
    check(commandHandle);
    check(force,3);
    check(position,3);
  }
  nb3ApplyExternalForce(commandHandle,bodyUniqueId,linkId,memAddress(force),memAddress(position),flag);
}
