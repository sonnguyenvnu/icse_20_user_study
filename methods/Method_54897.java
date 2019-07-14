public static void b3SetClosestDistanceFilterCollisionShapePositionA(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("double const *") DoubleBuffer collisionShapePositionA){
  if (CHECKS) {
    check(commandHandle);
    check(collisionShapePositionA,3);
  }
  nb3SetClosestDistanceFilterCollisionShapePositionA(commandHandle,memAddress(collisionShapePositionA));
}
