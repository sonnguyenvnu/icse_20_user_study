public static int b3PhysicsParameterSetConstraintSolverType(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("int const") int raintSolverType){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3PhysicsParameterSetConstraintSolverType(commandHandle,raintSolverType);
}
