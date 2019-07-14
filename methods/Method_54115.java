/** 
 * Remove a specific UV channel 'n'
 * @param n The UV channel
 */
public static int aiComponent_TEXCOORDSn(int n){
  return 1 << (n + 25);
}
