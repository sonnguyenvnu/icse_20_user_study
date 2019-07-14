/** 
 * Fills the specified buffer with zeros from the current position to the current limit.
 * @param buffer the buffer to fill with zeros
 */
public static void zeroBuffer(DoubleBuffer buffer){
  memSet(buffer,0);
}
