/** 
 * Creates a new struct type using a signature string.
 * @param signature the struct signature
 */
@NativeType("DCstruct *") public static long dcDefineStruct(@NativeType("char const *") ByteBuffer signature){
  if (CHECKS) {
    checkNT1(signature);
  }
  return ndcDefineStruct(memAddress(signature));
}
