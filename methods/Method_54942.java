public static void b3CreateCollisionShapeSetChildTransform(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int shapeIndex,@NativeType("double const *") DoubleBuffer childPosition,@NativeType("double const *") DoubleBuffer childOrientation){
  if (CHECKS) {
    check(commandHandle);
    check(childPosition,3);
    check(childOrientation,4);
  }
  nb3CreateCollisionShapeSetChildTransform(commandHandle,shapeIndex,memAddress(childPosition),memAddress(childOrientation));
}
