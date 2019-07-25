/** 
 * <p>Verified.
 */
public static double fmod(double pValue,double pMod){
  while (pValue < 0) {
    pValue+=pMod;
  }
  while (pValue > pMod) {
    pValue-=pMod;
  }
  return pValue;
}
