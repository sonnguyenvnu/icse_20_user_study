public static int b3GetUserDataId(@NativeType("b3PhysicsClientHandle") long physClient,int bodyUniqueId,int linkIndex,int visualShapeIndex,@NativeType("char const *") CharSequence key){
  if (CHECKS) {
    check(physClient);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(key,true);
    long keyEncoded=stack.getPointerAddress();
    return nb3GetUserDataId(physClient,bodyUniqueId,linkIndex,visualShapeIndex,keyEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
