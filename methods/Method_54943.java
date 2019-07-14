public static int b3CreateVisualShapeAddPlane(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("double const *") DoubleBuffer planeNormal,double planeConstant){
  if (CHECKS) {
    check(commandHandle);
    check(planeNormal,3);
  }
  return nb3CreateVisualShapeAddPlane(commandHandle,memAddress(planeNormal),planeConstant);
}
