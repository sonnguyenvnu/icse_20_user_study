public static int b3PhysicsParamSetDefaultNonContactERP(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double defaultNonContactERP){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParamSetDefaultNonContactERP(commandHandle,defaultNonContactERP);
}
