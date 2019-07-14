/** 
 * Unsafe version of  {@link #szDeviceString}. 
 */
public static String nszDeviceString(long struct){
  return memUTF16(struct + MONITORINFOEX.SZDEVICE);
}
