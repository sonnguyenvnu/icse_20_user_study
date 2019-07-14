public static int b3LoadSdfCommandSetUseMultiBody(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int useMultiBody){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3LoadSdfCommandSetUseMultiBody(commandHandle,useMultiBody);
}
