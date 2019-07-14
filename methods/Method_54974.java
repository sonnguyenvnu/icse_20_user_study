public static int b3LoadSoftBodySetCollisionMargin(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double collisionMargin){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3LoadSoftBodySetCollisionMargin(commandHandle,collisionMargin);
}
