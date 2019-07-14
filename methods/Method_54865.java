public static int b3InitChangeUserConstraintSetFrameInB(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("double const *") DoubleBuffer jointChildFrameOrn){
  if (CHECKS) {
    check(commandHandle);
    check(jointChildFrameOrn,4);
  }
  return nb3InitChangeUserConstraintSetFrameInB(commandHandle,memAddress(jointChildFrameOrn));
}
