public static int b3CreateBoxCommandSetCollisionShapeType(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int collisionShapeType){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3CreateBoxCommandSetCollisionShapeType(commandHandle,collisionShapeType);
}
