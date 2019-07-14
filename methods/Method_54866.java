public static int b3InitChangeUserConstraintSetGearAuxLink(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,int gearAuxLink){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3InitChangeUserConstraintSetGearAuxLink(commandHandle,gearAuxLink);
}
