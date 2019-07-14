@NativeType("b3SharedMemoryCommandHandle") public static long b3RemovePickingConstraint(@NativeType("b3PhysicsClientHandle") long physClient){
  if (CHECKS) {
    check(physClient);
  }
  return nb3RemovePickingConstraint(physClient);
}
