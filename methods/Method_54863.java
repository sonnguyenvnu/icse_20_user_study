public static int b3ChangeDynamicsInfoSetActivationState(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int activationState){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3ChangeDynamicsInfoSetActivationState(commandHandle,bodyUniqueId,activationState);
}
