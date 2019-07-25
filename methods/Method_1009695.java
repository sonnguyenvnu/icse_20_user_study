/** 
 * Return the crc whose masked representation is masked_crc.
 */
public static int unmask(int maskedCrc){
  int rot=maskedCrc - MASK_DELTA;
  return ((rot >>> 17) | (rot << 15));
}
