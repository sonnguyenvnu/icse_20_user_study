/** 
 * Return the fingerprint of the bytes in the array <code>bytes</code>. 
 */
public static long New(byte[] bytes){
  return Extend(IrredPoly,bytes,0,bytes.length);
}
