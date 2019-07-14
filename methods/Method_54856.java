public static int b3ChangeDynamicsInfoSetLateralFriction(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkIndex,double lateralFriction){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3ChangeDynamicsInfoSetLateralFriction(commandHandle,bodyUniqueId,linkIndex,lateralFriction);
}
