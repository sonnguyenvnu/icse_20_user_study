public static void b3RaycastBatchAddRay(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("double const *") DoubleBuffer rayFromWorld,@NativeType("double const *") DoubleBuffer rayToWorld){
  if (CHECKS) {
    check(commandHandle);
    check(rayFromWorld,3);
    check(rayToWorld,3);
  }
  nb3RaycastBatchAddRay(commandHandle,memAddress(rayFromWorld),memAddress(rayToWorld));
}
