/** 
 * Return the fingerprint of the characters in the array <code>c</code>. 
 */
public static long New(char[] c){
  return Extend(IrredPoly,c,0,c.length);
}
