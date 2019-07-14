public static int b3GetLinkState(@NativeType("b3PhysicsClientHandle") long physClient,@NativeType("b3SharedMemoryStatusHandle") long statusHandle,int linkIndex,@NativeType("struct b3LinkState *") B3LinkState state){
  if (CHECKS) {
    check(physClient);
    check(statusHandle);
  }
  return nb3GetLinkState(physClient,statusHandle,linkIndex,state.address());
}
