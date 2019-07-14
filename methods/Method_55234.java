/** 
 * Creates a new protocol instance. <p>You must register the returned protocol instance with the  {@link #objc_registerProtocol} function before you can use it.</p><p>There is no dispose method associated with this function.</p>
 * @param name the name of the protocol you want to create
 * @return a new protocol instance or {@link #nil} if a protocol with the same name as {@code name} already exists
 */
@NativeType("Protocol *") public static long objc_allocateProtocol(@NativeType("char const *") ByteBuffer name){
  if (CHECKS) {
    checkNT1(name);
  }
  return nobjc_allocateProtocol(memAddress(name));
}
