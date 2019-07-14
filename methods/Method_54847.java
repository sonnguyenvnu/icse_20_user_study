public static int b3ComputeDofCount(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId){
  if (CHECKS) {
    check(physClient);
  }
  return nb3ComputeDofCount(physClient,bodyUniqueId);
}
