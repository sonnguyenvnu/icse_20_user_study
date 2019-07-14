@NativeType("b3SharedMemoryCommandHandle") public static long b3InitRemoveUserDataCommand(@NativeType("b3PhysicsClientHandle") long physClient,int userDataId){
  if (CHECKS) {
    check(physClient);
  }
  return nb3InitRemoveUserDataCommand(physClient,userDataId);
}
