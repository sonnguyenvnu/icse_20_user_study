/** 
 * Return the fingerprint of the characters in the array <code>c</code>. 
 */
public static long New(char[] c,int off,int len){
  return Extend(IrredPoly,c,off,len);
}
