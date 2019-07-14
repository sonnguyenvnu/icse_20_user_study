/** 
 * Returns whether the decoder is known to fail when an attempt is made to reconfigure it with a new format's configuration data. <p>When enabled, the workaround will always release and recreate the decoder, rather than attempting to reconfigure the existing instance.
 * @param name The name of the decoder.
 * @return True if the decoder is known to fail when an attempt is made to reconfigure it with anew format's configuration data.
 */
private static boolean codecNeedsReconfigureWorkaround(String name){
  return Util.MODEL.startsWith("SM-T230") && "OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(name);
}
