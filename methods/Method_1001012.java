/** 
 * <p>Examples: min -2, max 2: range 4 <ul> <li> value 1: lFact = 0 <li> value 3: lFact = 1, value -1 <li> value 9: lFact = 2, value 1 <li> value -3: lFact = -1, value 1 </ul>
 */
public static double fmod(double pValue,double pMinValue,double pMaxValue){
  final double lRange=pMaxValue - pMinValue;
  int lFact=(int)((pValue - pMinValue) / lRange);
  if (pValue < pMinValue) {
    lFact--;
  }
  return pValue - lFact * lRange;
}
