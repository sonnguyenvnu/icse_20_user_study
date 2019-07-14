/** 
 * Returns <code>BigDecimal</code> value of JD.
 */
public BigDecimal toBigDecimal(){
  return new BigDecimal(integer).add(new BigDecimal(fraction));
}
