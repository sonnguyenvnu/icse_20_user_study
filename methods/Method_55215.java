/** 
 * Returns by reference a string describing a single parameter type of a method. <p>The parameter type string is copied to  {@code dst}.  {@code dst} is filled as if {@code strncpy(dst, parameter_type, dst_len)} were called. If themethod contains no parameter with that index,  {@code dst} is filled as if {@code strncpy(dst, "", dst_len)} were called.</p>
 * @param m       the method you want to inquire about
 * @param index   the index of the parameter you want to inquire about
 * @param dst_len the maximum number of characters that can be stored in {@code dst}
 */
@NativeType("void") public static String method_getArgumentType(@NativeType("Method") long m,@NativeType("unsigned int") int index,@NativeType("size_t") long dst_len){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    ByteBuffer dst=stack.malloc((int)dst_len);
    nmethod_getArgumentType(m,index,memAddress(dst),dst_len);
    return memUTF8(memByteBufferNT1(memAddress(dst),(int)dst_len));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
