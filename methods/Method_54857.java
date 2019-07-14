public static int b3ChangeDynamicsInfoSetSpinningFriction(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkIndex,double friction){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3ChangeDynamicsInfoSetSpinningFriction(commandHandle,bodyUniqueId,linkIndex,friction);
}
