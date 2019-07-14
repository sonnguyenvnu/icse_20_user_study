public static int b3PhysicsParamSetTimeStep(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double timeStep){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParamSetTimeStep(commandHandle,timeStep);
}
