public static int b3ChangeDynamicsInfoSetCcdSweptSphereRadius(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkIndex,double ccdSweptSphereRadius){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3ChangeDynamicsInfoSetCcdSweptSphereRadius(commandHandle,bodyUniqueId,linkIndex,ccdSweptSphereRadius);
}
