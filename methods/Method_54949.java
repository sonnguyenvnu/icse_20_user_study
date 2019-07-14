public static int b3CreateMultiBodyBase(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double mass,int collisionShapeUnique,int visualShapeUniqueId,@NativeType("double const *") DoubleBuffer basePosition,@NativeType("double const *") DoubleBuffer baseOrientation,@NativeType("double const *") DoubleBuffer baseInertialFramePosition,@NativeType("double const *") DoubleBuffer baseInertialFrameOrientation){
  if (CHECKS) {
    check(commandHandle);
    check(basePosition,3);
    check(baseOrientation,4);
    check(baseInertialFramePosition,3);
    check(baseInertialFrameOrientation,4);
  }
  return nb3CreateMultiBodyBase(commandHandle,mass,collisionShapeUnique,visualShapeUniqueId,memAddress(basePosition),memAddress(baseOrientation),memAddress(baseInertialFramePosition),memAddress(baseInertialFrameOrientation));
}
