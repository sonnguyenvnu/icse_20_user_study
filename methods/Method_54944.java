public static int b3CreateVisualShapeAddMesh(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("char const *") CharSequence fileName,@NativeType("double const *") DoubleBuffer meshScale){
  if (CHECKS) {
    check(commandHandle);
    check(meshScale,3);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(fileName,true);
    long fileNameEncoded=stack.getPointerAddress();
    return nb3CreateVisualShapeAddMesh(commandHandle,fileNameEncoded,memAddress(meshScale));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
