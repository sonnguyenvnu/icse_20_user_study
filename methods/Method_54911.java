public static int b3PhysicsParameterSetAllowedCcdPenetration(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double allowedCcdPenetration){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParameterSetAllowedCcdPenetration(commandHandle,allowedCcdPenetration);
}
