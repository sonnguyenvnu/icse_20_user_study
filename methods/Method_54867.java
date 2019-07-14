public static int b3GetNumUserConstraints(@NativeType("b3PhysicsClientHandle") long physClient){
  if (CHECKS) {
    check(physClient);
  }
  return nb3GetNumUserConstraints(physClient);
}
