public static int b3ChangeDynamicsInfoSetContactProcessingThreshold(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkIndex,double contactProcessingThreshold){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3ChangeDynamicsInfoSetContactProcessingThreshold(commandHandle,bodyUniqueId,linkIndex,contactProcessingThreshold);
}
