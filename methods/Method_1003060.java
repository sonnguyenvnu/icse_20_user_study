/** 
 * Shift the value 1 byte left, and add the byte at index inPos+2.
 */
private static int next(int v,byte[] in,int inPos){
  return (v << 8) | (in[inPos + 2] & 255);
}
