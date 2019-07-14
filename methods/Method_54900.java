public static void b3GetVisualShapeInformation(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("struct b3VisualShapeInformation *") B3VisualShapeInformation visualShapeInfo){
  if (CHECKS) {
    check(physClient);
  }
  nb3GetVisualShapeInformation(physClient,visualShapeInfo.address());
}
