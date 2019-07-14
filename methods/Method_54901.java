@NativeType("b3SharedMemoryCommandHandle") public static long b3InitLoadTexture(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("char const *") ByteBuffer filename){
  if (CHECKS) {
    check(physClient);
    checkNT1(filename);
  }
  return nb3InitLoadTexture(physClient,memAddress(filename));
}
