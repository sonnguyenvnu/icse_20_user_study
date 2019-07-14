/** 
 * Returns a specified protocol. <p>This function acquires the runtime lock.</p>
 * @param name the name of a protocol
 * @return the protocol named {@code name}{, or  {@code NULL} if no protocol named name could be found
 */
@NativeType("Protocol *") public static long objc_getProtocol(@NativeType("char const *") CharSequence name){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF8(name,true);
    long nameEncoded=stack.getPointerAddress();
    return nobjc_getProtocol(nameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
