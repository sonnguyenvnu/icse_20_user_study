public static int b3CreateCollisionShapeAddSphere(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double radius){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3CreateCollisionShapeAddSphere(commandHandle,radius);
}
