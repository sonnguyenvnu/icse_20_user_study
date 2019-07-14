public static int b3CreateCollisionShapeAddCapsule(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double radius,double height){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3CreateCollisionShapeAddCapsule(commandHandle,radius,height);
}
