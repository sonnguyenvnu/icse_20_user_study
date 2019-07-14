public static int b3LoadSoftBodySetMass(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double mass){
  if (CHECKS) {
    check(commandHandle);
  }
  return nb3LoadSoftBodySetMass(commandHandle,mass);
}
