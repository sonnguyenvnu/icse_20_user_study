/** 
 * Unsafe version of  {@link #vendorId}. 
 */
public static short nvendorId(long struct){
  return UNSAFE.getShort(null,struct + BGFXCapsGPU.VENDORID);
}
