public static int b3PhysicsParamSetMaxNumCommandsPer1ms(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int maxNumCmdPer1ms){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParamSetMaxNumCommandsPer1ms(commandHandle,maxNumCmdPer1ms);
}
