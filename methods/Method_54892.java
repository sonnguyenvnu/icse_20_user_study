public static void b3GetContactPointInformation(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("struct b3ContactInformation *") B3ContactInformation contactPointData){
  if (CHECKS) {
    check(physClient);
  }
  nb3GetContactPointInformation(physClient,contactPointData.address());
}
