public static int b3LoadStateSetFileName(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("char const *") CharSequence fileName){
  if (CHECKS) {
    check(commandHandle);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(fileName,true);
    long fileNameEncoded=stack.getPointerAddress();
    return nb3LoadStateSetFileName(commandHandle,fileNameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
