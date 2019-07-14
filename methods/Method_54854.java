public static int b3ChangeDynamicsInfoSetMass(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkIndex,double mass){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3ChangeDynamicsInfoSetMass(commandHandle,bodyUniqueId,linkIndex,mass);
}
