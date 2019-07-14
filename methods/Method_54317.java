/** 
 * Unsafe version of  {@link #deviceId}. 
 */
public static short ndeviceId(long struct){
  return UNSAFE.getShort(null,struct + BGFXCapsGPU.DEVICEID);
}
