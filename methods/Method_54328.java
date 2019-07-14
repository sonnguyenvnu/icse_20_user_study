/** 
 * Unsafe version of  {@link #maxEncoders}. 
 */
public static short nmaxEncoders(long struct){
  return UNSAFE.getShort(null,struct + BGFXInitLimits.MAXENCODERS);
}
