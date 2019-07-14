public static int b3ChangeDynamicsInfoSetFrictionAnchor(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int bodyUniqueId,int linkIndex,int frictionAnchor){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3ChangeDynamicsInfoSetFrictionAnchor(commandHandle,bodyUniqueId,linkIndex,frictionAnchor);
}
