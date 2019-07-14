public static int b3StateLoggingStart(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int loggingType,@NativeType("char const *") CharSequence fileName){
  if (CHECKS) {
    check(commandHandle);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(fileName,true);
    long fileNameEncoded=stack.getPointerAddress();
    return nb3StateLoggingStart(commandHandle,loggingType,fileNameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
