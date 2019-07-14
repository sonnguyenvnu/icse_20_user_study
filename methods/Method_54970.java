@NativeType("b3SharedMemoryCommandHandle") public static long b3LoadSoftBodyCommandInit(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("char const *") ByteBuffer fileName){
  if (CHECKS) {
    check(physClient);
    checkNT1(fileName);
  }
  return nb3LoadSoftBodyCommandInit(physClient,memAddress(fileName));
}
