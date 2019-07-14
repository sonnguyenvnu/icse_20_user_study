/** 
 * Returns by reference a string describing a method's return type. <p>The method's return type string is copied to  {@code dst}.  {@code dst} is filled as if {@code strncpy(dst, parameter_type, dst_len)} were called.</p>
 * @param m       the method to inspect
 * @param dst_len the maximum number of characters that can be stored in {@code dst}
 */
@NativeType("void") public static String method_getReturnType(@NativeType("Method") long m,@NativeType("size_t") long dst_len){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    ByteBuffer dst=stack.malloc((int)dst_len);
    nmethod_getReturnType(m,memAddress(dst),dst_len);
    return memUTF8(memByteBufferNT1(memAddress(dst),(int)dst_len));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
