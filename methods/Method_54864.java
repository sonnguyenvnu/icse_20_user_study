public static int b3InitChangeUserConstraintSetPivotInB(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("double const *") DoubleBuffer jointChildPivot){
  if (CHECKS) {
    check(commandHandle);
    check(jointChildPivot,3);
  }
  return nb3InitChangeUserConstraintSetPivotInB(commandHandle,memAddress(jointChildPivot));
}
