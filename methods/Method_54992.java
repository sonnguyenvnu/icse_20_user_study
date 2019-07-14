/** 
 * Creates a new struct type using a signature string.
 * @param signature the struct signature
 */
@NativeType("DCstruct *") public static long dcDefineStruct(@NativeType("char const *") CharSequence signature){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(signature,true);
    long signatureEncoded=stack.getPointerAddress();
    return ndcDefineStruct(signatureEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
