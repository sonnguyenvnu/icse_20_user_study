/** 
 * Unsafe version of  {@link #name(ByteBuffer) name}. 
 */
public static void nname(long struct,ByteBuffer value){
  if (CHECKS) {
    checkNT1(value);
  }
  memPutAddress(struct + ObjCPropertyAttribute.NAME,memAddress(value));
}
