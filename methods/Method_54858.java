public static int b3ChangeDynamicsInfoSetRollingFriction(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkIndex,double friction){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3ChangeDynamicsInfoSetRollingFriction(commandHandle,bodyUniqueId,linkIndex,friction);
}
