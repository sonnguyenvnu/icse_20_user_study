/** 
 * Send physics commands using TCP networking. 
 */
@NativeType("b3PhysicsClientHandle") public static long b3ConnectPhysicsTCP(@Nullable @NativeType("char const *") CharSequence hostName,int port){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCIISafe(hostName,true);
    long hostNameEncoded=hostName == null ? NULL : stack.getPointerAddress();
    return nb3ConnectPhysicsTCP(hostNameEncoded,port);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
