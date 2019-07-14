/** 
 * Returns a specified protocol. <p>This function acquires the runtime lock.</p>
 * @param name the name of a protocol
 * @return the protocol named {@code name}{, or  {@code NULL} if no protocol named name could be found
 */
@NativeType("Protocol *") public static long objc_getProtocol(@NativeType("char const *") ByteBuffer name){
  if (CHECKS) {
    checkNT1(name);
  }
  return nobjc_getProtocol(memAddress(name));
}
