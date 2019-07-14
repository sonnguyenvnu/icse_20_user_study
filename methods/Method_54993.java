/** 
 * Creates and initializes a new  {@code Callback} object.<p>Use  {@link #dcbFreeCallback FreeCallback} to destroy the {@code Callback} object.</p>
 * @param signature the function signature of the function to mimic
 * @param funcptr   a pointer to a callback handler
 * @param userdata  a pointer to custom data that might be useful in the handler
 */
@NativeType("DCCallback *") public static long dcbNewCallback(@NativeType("char const *") CharSequence signature,@NativeType("DCCallbackHandler *") long funcptr,@NativeType("void *") long userdata){
  if (CHECKS) {
    check(funcptr);
    check(userdata);
  }
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(signature,true);
    long signatureEncoded=stack.getPointerAddress();
    return ndcbNewCallback(signatureEncoded,funcptr,userdata);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
