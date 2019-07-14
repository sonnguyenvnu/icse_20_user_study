public static void b3GetAABBOverlapResults(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("struct b3AABBOverlapData *") B3AABBOverlapData data){
  if (CHECKS) {
    check(physClient);
  }
  nb3GetAABBOverlapResults(physClient,data.address());
}
