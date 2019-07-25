/** 
 * ?? a ???? b
 * @param a
 * @param b
 * @return a&gt;b ??true, a&lt;=b ?? false
 */
public static boolean bigger(double a,double b){
  BigDecimal v1=BigDecimal.valueOf(a);
  BigDecimal v2=BigDecimal.valueOf(b);
  if (v1.compareTo(v2) == 1) {
    return true;
  }
  return false;
}
