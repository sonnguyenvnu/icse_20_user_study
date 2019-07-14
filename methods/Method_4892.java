/** 
 * Returns whether the device needs keys to have been loaded into the  {@link DrmSession} beforecodec configuration.
 */
private boolean deviceNeedsDrmKeysToConfigureCodecWorkaround(){
  return "Amazon".equals(Util.MANUFACTURER) && ("AFTM".equals(Util.MODEL) || "AFTB".equals(Util.MODEL));
}
