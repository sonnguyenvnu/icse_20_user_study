public static int b3GetNumJoints(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId){
  if (CHECKS) {
    check(physClient);
  }
  return nb3GetNumJoints(physClient,bodyUniqueId);
}
