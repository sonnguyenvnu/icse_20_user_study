public static int b3ChangeDynamicsInfoSetAngularDamping(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,double angularDamping){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3ChangeDynamicsInfoSetAngularDamping(commandHandle,bodyUniqueId,angularDamping);
}
