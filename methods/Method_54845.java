public static int b3GetBodyUniqueId(@NativeType("b3PhysicsClientHandle") long physClient,int serialIndex){
  if (CHECKS) {
    check(physClient);
  }
  return nb3GetBodyUniqueId(physClient,serialIndex);
}
