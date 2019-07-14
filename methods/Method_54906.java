public static int b3PhysicsParamSetDefaultFrictionCFM(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double frictionCFM){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParamSetDefaultFrictionCFM(commandHandle,frictionCFM);
}
