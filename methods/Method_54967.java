public static void b3RaycastBatchAddRays(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("b3SharedMemoryCommandHandle") long commandHandle,@NativeType("double const *") DoubleBuffer rayFromWorld,@NativeType("double const *") DoubleBuffer rayToWorld){
  if (CHECKS) {
    check(physClient);
    check(commandHandle);
    check(rayToWorld,rayFromWorld.remaining());
  }
  nb3RaycastBatchAddRays(physClient,commandHandle,memAddress(rayFromWorld),memAddress(rayToWorld),rayFromWorld.remaining() / 3);
}
