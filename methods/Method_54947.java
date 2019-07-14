public static void b3CreateVisualShapeSetSpecularColor(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int shapeIndex,@NativeType("double const *") DoubleBuffer specularColor){
  if (CHECKS) {
    check(commandHandle);
    check(specularColor,3);
  }
  nb3CreateVisualShapeSetSpecularColor(commandHandle,shapeIndex,memAddress(specularColor));
}
